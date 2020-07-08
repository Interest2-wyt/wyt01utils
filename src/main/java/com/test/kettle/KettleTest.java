//package com.test.kettle;
//
//import org.pentaho.di.core.KettleEnvironment;
//import org.pentaho.di.core.database.DatabaseMeta;
//import org.pentaho.di.core.exception.KettleException;
//import org.pentaho.di.job.Job;
//import org.pentaho.di.job.JobMeta;
//import org.pentaho.di.repository.Repository;
//import org.pentaho.di.repository.RepositoryDirectoryInterface;
//import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
//import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
//import org.pentaho.di.trans.Trans;
//import org.pentaho.di.trans.TransMeta;
//
//public class KettleTest {
//    public  static KettleDatabaseRepository repository;
//    public  static DatabaseMeta databaseMeta;
//    public  static KettleDatabaseRepositoryMeta kettleDatabaseMeta;
//    public  static RepositoryDirectoryInterface directory;
//
//    /*
//     * KETTLE初始化*/
//    public static  String  KettleEnvironments() {
//        try {
//            KettleEnvironment.init();
//            repository = new KettleDatabaseRepository();
//            databaseMeta = new DatabaseMeta("ETL", "Postgres", "Native", "10.194.101.37","postgres", "5432",
//                    "postgres", "123456");//资源库数据库地址，我这里采用oracle数据库
//            kettleDatabaseMeta = new KettleDatabaseRepositoryMeta("ETL", "ERP",
//                    "Transformation description", databaseMeta);
//            repository.init(kettleDatabaseMeta);
//            repository.connect("", "");//资源库用户名和密码
//            directory = repository.loadRepositoryDirectoryTree();
//        } catch (KettleException e) {
//            e.printStackTrace();
//            return  e.getMessage();
//        }
//        return null;
//    }
//    /*执行转换文件*/
//    private static String ExecuteDataBaseRepTran(KettleDatabaseRepository repository, String transName, String param[], String jgname) throws KettleException {
//        //根据变量查找到模型所在的目录对象,此步骤很重要。
//        RepositoryDirectoryInterface directory = repository.findDirectory("/");
//        //创建ktr元对象
//        TransMeta transformationMeta = ((Repository) repository).loadTransformation(transName, directory, null, true, null);
//        //创建ktr
//        Trans trans = new Trans(transformationMeta);
//        //执行ktr
//        trans.execute(param);
//        //等待执行完毕
//        trans.waitUntilFinished();
//        if (trans.getErrors() > 0) {
//            return "NO";
//        } else {
//            return "OK";
//        }
//    }
//    /*执行JOB文件*/
//    private static String execRepositoryJobs(KettleDatabaseRepository repository, RepositoryDirectoryInterface directory, String jobName, String jhid) throws KettleException {
//        JobMeta jobMeta = ((Repository) repository).loadJob(jobName, directory, null, null);
//        Job job = new Job(repository, jobMeta);
//        job.setVariable("id", jhid);
//        job.start();
//        job.waitUntilFinished();
//        if (job.getErrors() > 0) {
//            return "NO";
//        } else {
//            return "OK";
//        }
//    }
//
//    public static void main(String[] args) {
//        KettleEnvironments();
//        String transName=null;//转换或作业名称
//        String[] params=null;//参数
//        try {
//            String result = ExecuteDataBaseRepTran(repository, transName, params, null);
//        } catch (KettleException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
