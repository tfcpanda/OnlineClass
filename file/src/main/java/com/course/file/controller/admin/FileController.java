package com.course.file.controller.admin;

import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.FileService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/file")
public class FileController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    public static final String BUSINESS_NAME = "文件";
    @Resource
    private FileService fileService;


    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        LOG.info("PageDto:{}", pageDto);
        ResponseDto responseDto = new ResponseDto();
        fileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     *保存id，id有值的时候更新，没有值的时候增加
     * @param fileDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody FileDto fileDto) {
        ResponseDto responseDto = new ResponseDto();

        //后端校验填入信息


            ValidatorUtil.require(fileDto.getPath(), "相对路径");
            ValidatorUtil.length(fileDto.getPath(), "相对路径", 1, 100);


            ValidatorUtil.length(fileDto.getName(), "文件名", 1, 100);


            ValidatorUtil.length(fileDto.getSuffix(), "后缀", 1, 10);








        fileService.save(fileDto);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    /**
     *删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        LOG.info("id:{}", id);
        fileService.delete(id);

        return responseDto;
    }
}
