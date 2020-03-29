package itcast.heima.controller;

import itcast.heima.domain.*;
import itcast.heima.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ModelAndView UserLogin(User user){
        ModelAndView modelAndView = new ModelAndView();
        User user1 = userService.UserLogin(user);
        if(user1 !=null ){
            modelAndView.setViewName("main");
            return modelAndView;
        }else{
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public ModelAndView register(User user){
        ModelAndView modelAndView = new ModelAndView();
            User user1 = userService.UserLogin(user);
            if(user1 == null){
                userService.insertUser(user);
                modelAndView.addObject("register","注册成功,请登录");
                modelAndView.setViewName("login");
                return modelAndView;
            }else {
                modelAndView.addObject("register","该账号已经被使用，请重新输入");
                modelAndView.setViewName("register");
                return modelAndView;
            }


    }


   //待推荐乐器种类
    public List<String> lq(){
        List<String> list = new ArrayList<>();
        list.add("吉他");list.add("架子鼓");list.add("口琴");list.add("钢琴");
        list.add("二胡");list.add("古筝");list.add("琵琶");list.add("萨克斯");
        list.add("手风琴");
        return list;
    }


    ModelAndView modelAndView = new ModelAndView();
    //构建用户-项目评分表(数据集)
    Matrix ratingsMatrixs = lqtj.readFile("C:\\Users\\T470P\\Desktop\\u.rate");

    //老用户属性矩阵构建
    Matrix yonghuMatri =  lqtj.readFileAsyonghu("C:\\Users\\T470P\\Desktop\\u.chara");

    //构建项目特征矩阵
    Matrix itemsFeatureMatrixs = lqtj.readFileAs("C:\\Users\\T470P\\Desktop\\lq.chara");

    Matrix testratingsMatrixs;//用户评分数据--前端
    Matrix User_Matrixs;//老用户属性--前端

    Matrix useruserxsd;//老用户权重因子矩阵

    Matrix ItemMatrix;//项目特征相似度矩阵
    @ResponseBody
    @RequestMapping(value = "/item_Chara",method= RequestMethod.POST)
    public void Item_Chara(@RequestBody ItemList itemList){
        System.out.println("后");
        //待测数据，页面上获得 测试数据前端页面接受的数据 ,//构建待推荐的用户-项目评分表
        testratingsMatrixs = SparseMatrix.Factory.zeros(1, 9);

        for(Item_Chara item_chara : itemList.getItem_charaList()){
            testratingsMatrixs.setAsDouble(item_chara.getRate(), 0, item_chara.getInstrument());
        }
        //--------------------------------------------------
         WYYLcc cbf1 = new WYYLcc(ratingsMatrixs, itemsFeatureMatrixs,testratingsMatrixs);
         ItemMatrix = cbf1.WYYLAverage(); //返回项目特征相似度矩阵

    }

    @RequestMapping("/recommend")
    @ResponseBody
    public ModelAndView Recommend(User_Attr user_attr){
        System.out.println("先");
        System.out.println(user_attr.toString());

        User_Matrixs = SparseMatrix.Factory.zeros(1, 3);
        int age = user_attr.getAge();
        //年龄入矩阵
        if(age<13) {
            User_Matrixs.setAsDouble(0, 0,0);
        }else if(age<19 && age>12){
            User_Matrixs.setAsDouble(1, 0,0);
        }else if(age<25 && age>18) {
            User_Matrixs.setAsDouble(2, 0,0);
        }else if(age<35 && age>24) {
            User_Matrixs.setAsDouble(3, 0,0);
        }else if(age<45 && age>34) {
            User_Matrixs.setAsDouble(4, 0,0);
        }else if(age<60 && age>44) {
            User_Matrixs.setAsDouble(5, 0,0);
        }else if( age>59) {
            User_Matrixs.setAsDouble(6, 0,0);
        }
        //性别入矩阵
        User_Matrixs.setAsDouble(user_attr.getSex(), 0,1);

        //职业入矩阵
        String strs[]= {"administrator","artist","doctor","educator","engineer","entertainment","executive","healthcare","homemaker","lawyer","librarian","marketing","none",
                "other","programmer","retired","salesman","scientist","student","technician","writer"};
        String jobs = user_attr.getOccupation();
        int job=0;
        for(int i=0;i<strs.length;i++) {
            if(strs[i].equals(jobs)) {
                job=i;
                continue;
            }
        }
        User_Matrixs.setAsDouble(job, 0,2);

        //老用户属性权重结果存入矩阵
        useruserxsd = SparseMatrix.Factory.zeros(User_Matrixs.getRowCount(), yonghuMatri.getRowCount());
        for(int i=0;i<User_Matrixs.getRowCount();i++) {
            for(int j=0;j<yonghuMatri.getRowCount();j++) {
                double a=0.;
                for(int t=0;t<yonghuMatri.getColumnCount();t++) {
                    if(User_Matrixs.getAsDouble(i,t)==yonghuMatri.getAsDouble(j,t)) {
                        a=a+1;
                    }
                }
                useruserxsd.setAsDouble(a/3, i,j);
            }
        }

        //将项目特征相似度传入计算结果
        userbase3 cbf = new userbase3(ratingsMatrixs,testratingsMatrixs,useruserxsd,ItemMatrix);
        Matrix UserItemMatrix = cbf.userbaseAverage3();//混合(相似度大小)

        List<String> result =new ArrayList<>(); //最终待推荐结果
        List<String>  result_lq = lq();//从集合中取
        //比较大小，得到推荐结果
        Double max1 = 0.0002; int yh1 =0;int yh2 =0;
        Double max2 = 0.0001;

        for(int t = 0; t < UserItemMatrix.getColumnCount();t++){
            if(UserItemMatrix.getAsDouble(0,t) > max1){
                max1 = UserItemMatrix.getAsDouble(0,t);
                yh1=t;
            }
        }
        for(int t = 0; t < UserItemMatrix.getColumnCount();t++){
            if(UserItemMatrix.getAsDouble(0,t) == max1){

            }else if(UserItemMatrix.getAsDouble(0,t) > max2){
                max2 = UserItemMatrix.getAsDouble(0,t);
                yh2=t;
            }
        }
        //计算第一个用户yh1   testratingsMatrixs--目标，ratingsMatrixs--数据集中用户
        for(int t =0;t<ratingsMatrixs.getColumnCount();t++){
            if(testratingsMatrixs.getAsDouble(0,t)==0 && ratingsMatrixs.getAsDouble(yh1,t) >0){
                result.add(result_lq.get(t));
            }
        }
        //计算第二个用户yh1   testratingsMatrixs--目标，ratingsMatrixs--数据集中用户
        for(int t =0;t<ratingsMatrixs.getColumnCount();t++){
            if(testratingsMatrixs.getAsDouble(0,t)==0 && ratingsMatrixs.getAsDouble(yh2,t) >0){
                result.add(result_lq.get(t));
            }
        }
        if(result.isEmpty()){
            modelAndView.addObject("result_tj","漏洞");
        }else {
            modelAndView.addObject("result_tj",result.get(0));
        }
        //request.setAttribute("info","22222") HttpServletRequest request;
        System.out.println(result+"result");
        modelAndView.setViewName("old_user_result");
        System.out.println("产生推荐");
        return modelAndView;
    }


    //新用户属性矩阵构建
    Matrix New_yonghuMatri =  lqtj.newreadFileAsyonghu("C:\\Users\\T470P\\Desktop\\newuser.chara");
    @RequestMapping("/new_recommend")
    @ResponseBody
    public ModelAndView new_recommend(New_User_Attr new_user_attr){
        System.out.println(new_user_attr.toString());

        //新用户属性结果---前端
        Matrix New_User_Matrixs = SparseMatrix.Factory.zeros(1, 5);
        int age = new_user_attr.getAge();
        //年龄入矩阵
        if(age<13) {
            New_User_Matrixs.setAsDouble(0, 0,0);
        }else if(age<19 && age>12){
            New_User_Matrixs.setAsDouble(1, 0,0);
        }else if(age<25 && age>18) {
            New_User_Matrixs.setAsDouble(2, 0,0);
        }else if(age<35 && age>24) {
            New_User_Matrixs.setAsDouble(3, 0,0);
        }else if(age<45 && age>34) {
            New_User_Matrixs.setAsDouble(4, 0,0);
        }else if(age<60 && age>44) {
            New_User_Matrixs.setAsDouble(5, 0,0);
        }else if( age>59) {
            New_User_Matrixs.setAsDouble(6, 0,0);
        }
        //性别入矩阵
        New_User_Matrixs.setAsDouble(new_user_attr.getSex(), 0,1);

        //职业入矩阵
        String strs[]= {"administrator","artist","doctor","educator","engineer","entertainment","executive","healthcare","homemaker","lawyer","librarian","marketing","none",
                "other","programmer","retired","salesman","scientist","student","technician","writer"};
        String jobs = new_user_attr.getOccupation();
        int job=0;
        for(int i=0;i<strs.length;i++) {
            if(strs[i].equals(jobs)) {
                job=i;
                continue;
            }
        }
        New_User_Matrixs.setAsDouble(job, 0,2);

        //地址入矩阵
        New_User_Matrixs.setAsDouble(new_user_attr.getAddress(), 0,3);
        //爱好入矩阵
        New_User_Matrixs.setAsDouble(new_user_attr.getMusic_type(), 0,4);

        System.out.println("矩阵值"+New_User_Matrixs.getAsDouble(0,2));

        //用户属性权重结果存入矩阵
        Matrix Newuserxsd = SparseMatrix.Factory.zeros(New_User_Matrixs.getRowCount(), New_yonghuMatri.getRowCount());
        for(int i=0;i<New_User_Matrixs.getRowCount();i++) {
            for(int j=0;j<New_yonghuMatri.getRowCount();j++) {
                double a=0.;
                for(int t=0;t<New_yonghuMatri.getColumnCount();t++) {
                    if(New_User_Matrixs.getAsDouble(i,t)==New_yonghuMatri.getAsDouble(j,t)) {
                        a=a+1;
                    }
                }
                System.out.println("a"+a);
                Newuserxsd.setAsDouble(a/5, i,j);
            }
        }

        List<String> user_result =new ArrayList<>(); //最终待推荐结果
        List<String>  result_lq = lq();//从集合中取
        //比较大小，得到推荐结果
        Double max1 = 0.0002; int yh1 =0;

        for(int t = 0; t < Newuserxsd.getColumnCount();t++){
            if(Newuserxsd.getAsDouble(0,t) > max1){
                max1 = Newuserxsd.getAsDouble(0,t);
                yh1=t;
            }
        }

        //计算用户yh1   testratingsMatrixs--目标，ratingsMatrixs--数据集中用户
        for(int t =0;t<ratingsMatrixs.getColumnCount();t++){
            if( ratingsMatrixs.getAsDouble(yh1,t) >0){
                user_result.add(result_lq.get(t));
            }
        }
        ModelAndView modelAndView1 = new ModelAndView();

        modelAndView1.addObject("result_tj",user_result.get(0));
        modelAndView1.setViewName("new_user_result");

       return modelAndView1;
    }

}
