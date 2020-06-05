package com.fenghuang.poetry.herd.web;

import com.fenghuang.poetry.herd.common.web.Resp;
import com.fenghuang.poetry.herd.service.provider.UserService;
import com.fenghuang.poetry.herd.web.model.resp.user.UserDetailInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author fuzq
 * @version 1.0
 * @Desc 用户
 * @date Created in 2020年05月10日 08:30
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/web/tool")
@Api(value = "【小工具】", tags = {"【小工具】"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ToolController {
    @Autowired
    private UserService userService;

    /**
     * 获取配置文件的路径
     *
     * @author Ginty
     * @date 2019/3/25 16:50
     * @param null
     * @return
     */
    @Value("${server.tomcat.basedir}")
    private String resourceDir;

    /**
     * 上传二维码图片
     *
     * @param file
     * @return
     */
    @ApiOperation(
            value = "用户列表接口",
            notes = "用户列表接口",
            response = UserDetailInfoVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @PostMapping("/upload/wx/{areaCode}")
    @ResponseBody
    public Object upload(MultipartFile file, @PathVariable("areaCode") String areaCode,
                         HttpServletRequest request) throws IOException {
        try {
            if (Objects.isNull(file)) {
                return new Resp(500, "请选择文件上传");
            }
            String fileName = file.getOriginalFilename();
            if (fileName != null && !"".equals(fileName)) {
                //文件上传的地址
//            String path = ResourceUtils.getURL("classpath:").getPath() + "static/images";
                Path path = Paths.get(resourceDir);
                //新建目录
                //文件后缀
                String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                String newFilename = null;
                if (StringUtils.isNotBlank(areaCode)) {
                    newFilename = areaCode + fileSuffix;
                } else {
                    newFilename = fileName;
                }
                String tempPath = path.toAbsolutePath().toString() + File.separator + newFilename;
                //获取文件的名称
                log.info("文件路径: {}", path);
                //替换文件名  用地区码来代替文件名称
                File newFile = new File(tempPath);
                if (!newFile.getParentFile().exists()) {
                    newFile.getParentFile().mkdirs();
                }
                //完成文件的上传
                file.transferTo(newFile);
            }

        } catch (Exception e) {
            return new Resp(500, "图片上传失败");
        }
        return new Resp(null);
    }

    /**
     * 上传二维码图片
     *
     * @param imageName
     * @return
     */
    @ApiOperation(
            value = "用户列表接口",
            notes = "用户列表接口",
            response = UserDetailInfoVo.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST"
    )
    @GetMapping("/show/wx/{imageName}")
    @ResponseBody
    public Object showImage(@PathVariable("imageName") String imageName,
                            HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        String upload = null;

        //获取商品图片目录
        Path path = Paths.get(resourceDir);
        upload = path.toAbsolutePath().toString() + File.separator + imageName;


        try {
            //获取图片存放路径
            String imgPath = upload;
            ips = new FileInputStream(new File(imgPath));
            String type = imageName.substring(imageName.indexOf(".") + 1);
            if (type.equals("png")) {
                response.setContentType("image/png");
            }
            if (type.equals("jpeg")) {
                response.setContentType("image/jpeg");
            }
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert out != null;
            out.close();
            ips.close();
        }
        return null;
    }
}
