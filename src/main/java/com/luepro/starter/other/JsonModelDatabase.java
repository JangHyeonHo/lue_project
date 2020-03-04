package com.spring.test2.app.etc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.annotation.AnnotationUtils;

import com.spring.test2.app.etc.annotation.JsonModelFieldIgnore;
import com.spring.test2.app.etc.annotation.PriKey;
import com.spring.test2.app.etc.annotation.Sequence;
import com.spring.test2.app.etc.annotation.Unique;
import com.spring.test2.app.jsonModel.JsonModelInterface;

public class JsonModelDatabase <T extends JsonModelInterface> {

    private String folder;
    private String fileName;
    private List<T> cacheData;
    private boolean allDataCalling;
    /**default = false*/
    private boolean isDataModify;
    /**default = true*/
    private boolean cacheCheck;
    /**default = 30000(30 second)*/
    private long cacheTime;
    private long cacheStartTime;

    public JsonModelDatabase(String folder, String fileName) {
        this.folder = folder;
        this.fileName = fileName;
        this.allDataCalling = false;
        this.cacheCheck = true;
        this.cacheStartTime = 0;
        this.cacheTime = 30000;
        this.isDataModify = false;
    }

    public JsonModelDatabase(String folder, String fileName, boolean cacheCheck) {
        this.folder = folder;
        this.fileName = fileName;
        this.allDataCalling = false;
        this.cacheCheck = cacheCheck;
        this.cacheStartTime = 0;
        this.cacheTime = 30000;
        this.isDataModify = false;
    }

    public JsonModelDatabase(String folder, String fileName, boolean cacheCheck, long cacheTime) {
        this.folder = folder;
        this.fileName = fileName;
        this.allDataCalling = false;
        this.cacheCheck = cacheCheck;
        this.cacheStartTime = 0;
        this.cacheTime = cacheTime;
        this.isDataModify = false;
    }

    public String ModelOnJson(T jsonModel){
        if(jsonModel==null){
            return null;
        }
        System.out.println("Model Name : " + jsonModel.thisModelName());
        StringBuffer sb = new StringBuffer();
        sb.append("\"");
        sb.append(jsonModel.thisModelName());
        sb.append("\"");
        sb.append(" : [");
        sb.append("\n");
        Field[] modelFields = jsonModel.getClass().getDeclaredFields();
        String oneData = jsonOneCreate(modelFields,jsonModel);
        if(oneData.isEmpty() || oneData == null){
            return null;
        }
        sb.append(oneData);
        sb.append("\n");
        sb.append(" ]");
        return sb.toString();
    }

    public String ModelOnJson(List<T> jsonModels){
        if(jsonModels.isEmpty() || jsonModels==null){
            return null;
        }
        System.out.println("Model Name : " + jsonModels.get(0).thisModelName());
        StringBuffer sb = new StringBuffer();
        sb.append("\"");
        sb.append(jsonModels.get(0).thisModelName());
        sb.append("\"");
        sb.append(" : [");
        sb.append("\n");
        for(T jsonModel : jsonModels){
            Field[] modelFields = jsonModel.getClass().getDeclaredFields();
            String oneData = jsonOneCreate(modelFields,jsonModel);
            if(oneData.isEmpty() || oneData == null){
                continue;
            }
            sb.append(oneData);
            sb.append(",");
        }
        int lastCommaIndexOf = 0;
        if((lastCommaIndexOf = sb.lastIndexOf(",")) != -1){
            sb = sb.deleteCharAt(lastCommaIndexOf);
        }
        sb.append("\n");
        sb.append(" ]");
        return sb.toString();
    }

    private String jsonOneCreate(Field[] fields,T jsonModel){
        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        for(Field field : fields){
            if(AnnotationUtils.getAnnotation(field, JsonModelFieldIgnore.class) != null){
                continue;
            }
            Object obj = null;
            try {
                field.setAccessible(true);
                obj = field.get(jsonModel);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                System.out.println("err");
                continue;
            }
            String jsonFieldName = field.getName();
            if(field.getType() == String.class){
                sb.append(doubleQuatationInputMessage(jsonFieldName));
                sb.append(" : ");
                String stringObj = (String) obj;
                sb.append(doubleQuatationInputMessage(stringObj));
                sb.append(", ");
            }else if(field.getType() == int.class){
                sb.append(doubleQuatationInputMessage(jsonFieldName));
                sb.append(" : ");
                Integer integerObj = (Integer) obj;
                sb.append(doubleQuatationInputMessage(integerObj.toString()));
                sb.append(", ");
            }else if(field.getType() == long.class){
                sb.append(doubleQuatationInputMessage(jsonFieldName));
                sb.append(" : ");
                Long longObj = (Long) obj;
                sb.append(doubleQuatationInputMessage(longObj.toString()));
                sb.append(", ");
            }else if(field.getType() == double.class){
                sb.append(doubleQuatationInputMessage(jsonFieldName));
                sb.append(" : ");
                Double doubleObj = (Double) obj;
                sb.append(doubleQuatationInputMessage(doubleObj.toString()));
                sb.append(", ");
            }else if(field.getType() == boolean.class){
                sb.append(doubleQuatationInputMessage(jsonFieldName));
                sb.append(" : ");
                Boolean booleanObj = (Boolean) obj;
                sb.append(doubleQuatationInputMessage(booleanObj.toString()));
                sb.append(", ");
            }
            continue;
        }
        int lastCommaIndexOf = sb.lastIndexOf(",");
        if((lastCommaIndexOf = sb.lastIndexOf(",")) != -1){
            sb = sb.deleteCharAt(lastCommaIndexOf);
        }
        sb.append(" }");
        return sb.toString();
    }

    @Deprecated
    public static String toFirstStringLowerCase(String str){
        char[] chars = str.toCharArray();
        String firstLowerCase = String.valueOf(chars[0]).toLowerCase();
        chars[0] = firstLowerCase.charAt(0);
        return String.valueOf(chars);
    }
    private String doubleQuatationInputMessage(String message){
        return "\""+message+"\"";
    }

  //jsonFileInsert
    public Integer insertManyData(List<T> jsonModels){
        allDataCalling = false;
        List<T> fileConfirm = null;
        fileConfirm = cacheData;
        if(!isUsingCacheData()){
            fileConfirm = jsonFileLoading();
        }
        int insertCounting = 0;
        for(T jsonModel : jsonModels){
            if(fileConfirm != null && !fileConfirm.isEmpty()){
                if(!jsonUniqueDataCheck(fileConfirm, jsonModel)){
                    return 0;
                }
            } else{
                fileConfirm = new ArrayList<T>();
            }
            jsonModel = jsonSequenceDataCheck(fileConfirm,jsonModel);
            fileConfirm.add(jsonModel);
            insertCounting++;
        }
        if(jsonFileSaving(ModelOnJson(fileConfirm))!=1){
            return 0;
        }
        return insertCounting;
    }

  private T jsonSequenceDataCheck(List<T> fileConfirm, T jsonModel) {
      Field[] fields = jsonModel.getClass().getDeclaredFields();
      Set<Field> sequenceFieldNames = new HashSet<Field>();
      for(Field field: fields){
          if((AnnotationUtils.getAnnotation(field, Sequence.class))==null ||
                  (AnnotationUtils.getAnnotation(field, JsonModelFieldIgnore.class))!=null){
              continue;
          }
          sequenceFieldNames.add(field);
      }
      if(fileConfirm==null || fileConfirm.isEmpty()){
          for(Field fieldName : sequenceFieldNames){
              Sequence sequenceValue = AnnotationUtils.getAnnotation(fieldName, Sequence.class);
              try {
//                  System.out.println(fieldName.getName());
                  fieldSetting(fieldName,jsonModel,String.valueOf(sequenceValue.firstValue()));
              } catch (IllegalArgumentException | IllegalAccessException e) {
                  continue;
              }
          }
          return jsonModel;
      }
      T endValue = fileConfirm.get(fileConfirm.size()-1);
      for(Field fieldName : sequenceFieldNames){
          Sequence sequenceValue = AnnotationUtils.getAnnotation(fieldName, Sequence.class);
          try {
              System.out.println("fieldName : " + fieldName.getName());
              fieldName.setAccessible(true);
              Object o = fieldName.get(endValue);
              if(!(o instanceof Integer)){
                  System.out.println("Class Cast Exception : " + fieldName + " is not Integer");
                  return null;
              }
              Integer endNumber = (Integer) o;
              int sequenceIncrementNumber = sequenceValue.incrementNumber();
              fieldSetting(fieldName,jsonModel,String.valueOf(endNumber+sequenceIncrementNumber));
          } catch (IllegalArgumentException | IllegalAccessException e) {
              continue;
          }
      }
      return jsonModel;
  }

//jsonFilesInsert
    public Integer insertOneData(T jsonModel){
        allDataCalling = false;
        List<T> fileConfirm = null;
        fileConfirm = cacheData;
        if(!isUsingCacheData()){
            fileConfirm = jsonFileLoading();
        }
        if(fileConfirm != null && !fileConfirm.isEmpty()){
            if(!jsonUniqueDataCheck(fileConfirm, jsonModel)){
                return 0;
            }
        } else{
            fileConfirm = new ArrayList<T>();
        }
        jsonModel = jsonSequenceDataCheck(fileConfirm,jsonModel);
        fileConfirm.add(jsonModel);
        return jsonFileSaving(ModelOnJson(fileConfirm));
    }


    private boolean jsonUniqueDataCheck(
            List<T> fileConfirm, T jsonModel) {
        Field[] fields = jsonModel.getClass().getDeclaredFields();
        Set<Field> uniqueFieldNames = new HashSet<Field>();
        for(Field field: fields){
            if(((AnnotationUtils.getAnnotation(field, PriKey.class))==null &&
                    (AnnotationUtils.getAnnotation(field, Unique.class))==null) ||
                    (AnnotationUtils.getAnnotation(field, JsonModelFieldIgnore.class))!=null){
                continue;
            }
            uniqueFieldNames.add(field);
        }
        for(T data : fileConfirm){
            if(!CodeMap.isEqual(data.thisModelName(), jsonModel.thisModelName())){
                continue;
            }
            for(Field fieldName : uniqueFieldNames){
                try {
                    fieldName.setAccessible(true);
                    Object originData = fieldName.get(data);
                    Object insertData = fieldName.get(jsonModel);
                    if(CodeMap.isEqual(originData, insertData)){
                        System.out.println("this data is exist so not unique : \nFieldName - "+fieldName.getName()+"\nFieldData - "+originData +" | "+insertData);
                        return false;
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    continue;
                }
            }
       }
        return true;
    }

    private Integer jsonFileSaving(String jsonMessage){
        File directory = new File(CodeMap.ROOT_DIRECTORY+folder);
        File jsonFile = new File(CodeMap.ROOT_DIRECTORY+folder+"/"+fileName+".json");
        if(!directory.exists()){
            directory.mkdirs();
        }
        try {
            jsonFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(jsonFile);
            Writer writer = new OutputStreamWriter(fos,"UTF-8");
            writer.write(jsonMessage);
            writer.flush();
            writer.close();
            isDataModify = true;
            fos.close();
            return 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    private List<T> jsonFileLoading(){
        @SuppressWarnings("unused")
        File directory = new File(CodeMap.ROOT_DIRECTORY+folder);
        File jsonFile = new File(CodeMap.ROOT_DIRECTORY+folder+"/"+fileName+".json");
        if(!jsonFile.exists()){
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(jsonFile);
            Reader reader = new InputStreamReader(fis,"UTF-8");
            BufferedReader bufReader = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            String line = null;
            while((line = bufReader.readLine()) != null){
                sb.append(line);
            }
            List<T> jsonList = JsonToList(sb);
            bufReader.close();
            reader.close();
            fis.close();
            return jsonList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private List<T> JsonToList(StringBuffer sb) {
        List<T> jsonList = new ArrayList<T>();
        try {
            int firstDoubleQuotationIndex = sb.indexOf("\"");
            String modelName = sb.substring(firstDoubleQuotationIndex+1, sb.indexOf("\"", firstDoubleQuotationIndex+1));
            String packageName = "com.spring.test2.app.jsonModel";
            Class<T> json = (Class<T>) Class.forName(packageName+"."+modelName);
            String[] cutDatas = jsonDataCutting(sb);
            for(int i = 0; i<cutDatas.length; i++){
                T jsonInter = json.newInstance();
                Map<String, Object> jsonData = linkedData(cutDatas[i]);
                if(jsonData == null){
                    continue;
                }
                jsonInter = insertJsonData(jsonData,jsonInter);
                jsonList.add((T)jsonInter);
            }
        } catch (ClassNotFoundException e) {

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(jsonList.isEmpty()){
            return null;
        }
        return jsonList;
    }

    private T insertJsonData(
            Map<String, Object> jsonData, T jsonInter) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields  = jsonInter.getClass().getDeclaredFields();
        int dataCheck = 0;
        for(Field field : fields){
            String fieldName = field.getName();
            Object val = jsonData.get(fieldName);
            if(val == null){
                continue;
            }
            fieldSetting(field,jsonInter,val);
            dataCheck++;
        }
        if(dataCheck == 0){
            return null;
        }
        return jsonInter;
    }

    private void fieldSetting(Field field, T jsonInter, Object val) throws IllegalArgumentException, IllegalAccessException{
//        System.out.println("fieldSetting Method proc\nparamater - field : " + field.getName() +" | "+field.getType()+ " | val : " + val+" | "+val.getClass().getName());
        field.setAccessible(true);
        if(field.getType()== String.class){
            field.set(jsonInter,(String) val);
        }else if(field.getType() == int.class){
            field.set(jsonInter,Integer.parseInt((String)val));
        }else if(field.getType() == long.class){
            field.set(jsonInter,Long.parseLong((String)val));
        }else if(field.getType() == double.class){
            field.set(jsonInter,Double.parseDouble((String)val));
        }else if(field.getType() == boolean.class){
            field.set(jsonInter,Boolean.parseBoolean((String)val) );
        }
    }


    private static Map<String,Object> linkedData(String datas) {
        datas = datas.replace("{", "");
        datas = datas.replace("}", "");
        datas = datas.replace("\"", "");
        datas = datas.replace("]", "");
        String[] data = datas.split(",");
        Map<String,Object> keyVals = new HashMap<String, Object>();
        for(String d : data){
            d = d.trim();
            if(d.isEmpty()){
                continue;
            }
            String[] keyVal = d.split(":");
            for(int i = 0; i <keyVal.length; i++){
                keyVal[i] = keyVal[i].trim();
            }
            keyVals.put(keyVal[0], keyVal[1]);
        }
        if(keyVals.isEmpty()){
            return null;
        }
        return keyVals;
    }

    private static String[] jsonDataCutting(StringBuffer sb) {
        String datas = sb.substring(sb.indexOf("[")+1);
        String[] cuttingData = datas.split("}");
        return cuttingData;
    }

    public List<T> getAllData(){
        isDataModify = false;
        if(allDataCalling){
            return cacheData;
        }
        if(cacheCheck = false){
            return jsonFileLoading();
        }
        long nowTime = System.currentTimeMillis();
        if(nowTime-cacheStartTime>cacheTime){
            allDataCalling = true;
            cacheStartTime = System.currentTimeMillis();
            return cacheData = jsonFileLoading();
        }
        return cacheData;
    }

    @SuppressWarnings("unchecked")
    public T getOneData(Map<String, Object> searchKeys){
        isDataModify = false;
        allDataCalling = false;
        if(!isUsingCacheData()){
            List<T> data = jsonFileLoading();
            cacheData = data;
        }
        if(cacheData == null || cacheData.isEmpty()){
            return null;
         }
        String modelName = cacheData.get(0).thisModelName();
        String packageName = cacheData.get(0).getClass().getPackage().getName();
        if(!isUsingCacheData()){
            cacheData = null;
        }
        try {
            Class<T> json = (Class<T>) Class.forName(packageName+"."+modelName);
            T searchKeysChangeModel = json.newInstance();
            searchKeysChangeModel = insertJsonData(searchKeys, searchKeysChangeModel);
            return getOneData(searchKeysChangeModel);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> getManyData(Map<String, Object> searchKeys){
        isDataModify = false;
        allDataCalling = false;
        if(!isUsingCacheData()){
            List<T> data = jsonFileLoading();
            cacheData = data;
        }
        if(cacheData == null || cacheData.isEmpty()){
            return null;
         }
        String modelName = cacheData.get(0).thisModelName();
        String packageName = cacheData.get(0).getClass().getPackage().getName();
        if(!isUsingCacheData()){
            cacheData = null;
        }
        try {
            Class<T> json = (Class<T>) Class.forName(packageName+"."+modelName);
            T searchKeysChangeModel = json.newInstance();
            searchKeysChangeModel = insertJsonData(searchKeys, searchKeysChangeModel);
            return getManyData(searchKeysChangeModel);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public T getOneData(T searchKeys){
        isDataModify = false;
        allDataCalling = false;
        Field[] fields = searchKeys.getClass().getDeclaredFields();
        if(isUsingCacheData()){
            return searchingData(fields, cacheData, searchKeys);
        }
        return searchingData(fields, jsonFileLoading(), searchKeys);
    }

    public List<T> getManyData(T searchKeys){
        isDataModify = false;
        allDataCalling = false;
        Field[] fields = searchKeys.getClass().getDeclaredFields();
        if(isUsingCacheData()){
            return searchingDatas(fields, cacheData, searchKeys);
        }
        return searchingDatas(fields, jsonFileLoading(), searchKeys);
    }


    private List<T> searchingDatas(Field[] fields, List<T> datas, T searchKeys){
        List<T> returnsData = new ArrayList<T>();
        for(T data : datas){
            if(!CodeMap.isEqual(data.thisModelName(), searchKeys.thisModelName())){
                continue;
            }
            int isTrueCount = 0;
            for(Field fieldName : fields){
                try {
                    fieldName.setAccessible(true);
                    Object searchData = fieldName.get(searchKeys);
                    if(searchData==null){
                        isTrueCount++;
                        continue;
                    }
                    Object originData = fieldName.get(data);
                    if(CodeMap.isEqual(originData, searchData)){
                        isTrueCount++;
                    } else{
                        isTrueCount--;
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    continue;
                }
            }
            if(isTrueCount == fields.length){
                returnsData.add(data);
            }
        }
        if(returnsData.isEmpty()){
            return null;
        }
        return returnsData;
    }

    private T searchingData(Field[] fields, List<T> datas, T searchKeys){
        List<T> dataConfirm = searchingDatas(fields,datas,searchKeys);
        if(dataConfirm == null){
            System.out.println("error! data is nothing");
            return null;
        }
        if(dataConfirm.size() > 1){
            System.out.println("error! this keys are many datas");
            return null;
        }
        return (T) dataConfirm.get(0);
    }

    public String getFolder() {
        return folder;
    }
    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isCacheCheck() {
        return cacheCheck;
    }
    public void setCacheCheck(boolean cacheCheck) {
        this.cacheCheck = cacheCheck;
    }

    public long getCacheTime() {
        return cacheTime;
    }
    public void setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
    }

    private boolean isUsingCacheData(){
        boolean timeCheck = ((System.currentTimeMillis()-cacheStartTime)<=cacheTime);
        if(!isDataModify && cacheCheck && cacheData!=null && timeCheck){
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public <S extends Object> List<S> getAllDataAboutOneColumns(String columnsName) {
        List<T> datas = getAllData();
        if(datas == null || datas.isEmpty()){
            return null;
        }
        Field[] fields = datas.get(0).getClass().getDeclaredFields();
        Field selectColumns = null;
        for(Field field : fields){
            if(CodeMap.isEqual(field.getName(), columnsName)){
                selectColumns = field;
            }
        }
        if(selectColumns==null){
            return null;
        }
        List<S> columnsData = new ArrayList<S>();
        for(T data : datas){
            try {
                selectColumns.setAccessible(true);
                S s = (S) selectColumns.get(data);
                columnsData.add(s);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return columnsData;
    }

    public <S extends Object> List<S> getAllDataAboutDistinctOneColumns(String columnsName) {
        List<S> allData = getAllDataAboutOneColumns(columnsName);
        if(allData == null || allData.isEmpty()){
            return null;
        }
        Set<S> setIterator = new HashSet<S>();
        for(S data : allData){
            setIterator.add(data);
        }
        allData = new ArrayList<S>();
        for(S iterData : setIterator){
            allData.add(iterData);
        }
        return allData;
    }



}
