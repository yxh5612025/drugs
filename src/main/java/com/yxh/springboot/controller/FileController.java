package com.yxh.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yxh.springboot.common.Result;

import com.yxh.springboot.entity.Drug;
import com.yxh.springboot.service.impl.DrugServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {
    @Value("${server.port}")
    private String port;
    private static final String ip ="http://localhost";
    /**
     * 上传接口
     * @param file
     * @return
     */
    @Resource
    DrugServiceImpl drugService;

    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException{
        String originalFilename = file.getOriginalFilename();

        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/files/"+ flag +"_"+originalFilename;
//        String md5 = SecureUtil.md5(rootFilePath);
//        Drug drug = new Drug();

//        LambdaQueryWrapper<Drug> wrapper = Wrappers.<Drug>lambdaQuery(Drug.class).eq(Drug::getMd5, md5);
//        Drug Drug = drugService.getOne(wrapper);
//        String imgpath;
//        if (Drug!=null){
//            imgpath=drug.getImgpath();
//        }else {
//            imgpath=ip+":"+port+"/file/download"+flag;
//        }
////        drug.setMd5(md5);
//        drug.setImgpath(imgpath);
//        drugService.save(drug);
        FileUtil.writeBytes(file.getBytes(),rootFilePath);
        return Result.success(ip+":"+port+"/file/download/"+flag);
    }

    /**
     * 下载接口
     * @param flag  uuid
     * @param response
     */
    @GetMapping("/download/{flag}")
    public void download(@PathVariable("flag")String flag, HttpServletResponse response){
        OutputStream os;//创建一个输出流对象
        String rootPath = System.getProperty("user.dir") + "/src/main/resources/files/";//定于上传文件的路径
        List<String> fileNames = FileUtil.listFileNames(rootPath);//获取所有的文件名称
        String file = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");//找到和参数一致的文件
        try {
            if (StrUtil.isNotEmpty(file)){
                response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(file,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes =FileUtil.readBytes(rootPath+file);//通过文件的路径读取文件字节流
                os= response.getOutputStream();//通过输出流返回文件
                 os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            System.out.println("文件下载失败");
            e.printStackTrace();
        }
    }
}
