package com.huajframe.seckill;

import com.huajframe.seckill.entity.User;
import com.huajframe.seckill.service.IUserService;
import com.huajframe.seckill.util.MD5Util;
import com.huajframe.seckill.vo.RespBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SellkillApplicationTests {

    private static final int USER_COUNT = 5000;

    @Autowired
    private IUserService userService;

    /**
     * 生成用户数据，将userToken写入文件
     *
     * @throws IOException
     */
    // @Test
    void contextLoads() throws IOException {
        List<User> userList = new ArrayList<>(USER_COUNT);
        for (int i = 1; i <= USER_COUNT; i++) {
            User user = new User();
            user.setId(18200000000L + i);
            user.setLoginCount(1);
            user.setNickname("test" + i);
            user.setSalt("asdfg1243");
            user.setRegisterDate(new Date());
            user.setPassword(
                    MD5Util.inputPassToDBPass("123456",
                            user.getSalt())
            );
            userList.add(user);
        }

        System.out.println("数据初始化完成，准备插入数据库中-------");

        //插入数据库中
        userService.saveBatch(userList);

        System.out.println("插入完成，准备获取token写入文件---------");

        //插入完成，准备获取token写入文件
        File file = new File("F:\\apache-jmeter-5.4.1\\TEST Result\\Seckill\\config.txt");
        if (file.exists()) {
            file.delete();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        userList.forEach(user -> {
            //获取到uuid
            String userToken = sendPostRequest(user.getId());
            //换行
            try {
                //写入文件
                writer.write(user.getId() + "," + userToken);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();
        System.out.println("执行完毕");
    }

    /**
     * 发送http请求，获取到token
     * @param mobile 电话号码
     * @return userToken
     */
    public static String sendPostRequest(Long mobile) {
        String url = "http://localhost:9090/login/doLogin";
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //设置参数
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("mobile", mobile);
        params.add("password", MD5Util.inputPassToFormPass("123456"));
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
        HttpMethod method = HttpMethod.POST;
        //执行HTTP请求，将返回的结构使用RestVO类格式化
        ResponseEntity<RespBean> response = client.exchange(url, method, requestEntity, RespBean.class);
        //获取到userToken
        return (String) response.getBody().getObj();
    }

    // @Test
    public void testHttp(){
        System.out.println(sendPostRequest(18212345678L));
    }


}
