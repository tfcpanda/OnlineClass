package com.course.server.service;

import com.course.server.domain.File;
import com.course.server.domain.FileExample;
import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.FileMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Resource
    private FileMapper fileMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        FileExample fileExample = new FileExample();
        List<File> fileList = fileMapper.selectByExample(fileExample);
        PageInfo<File> pageInfo = new PageInfo<>(fileList);
        pageDto.setTotal(pageInfo.getTotal());
//        List<FileDto> fileDtoList = new ArrayList<FileDto>();
//        for (int i = 0, l = fileList.size(); i < l; i++) {
//            File file = fileList.get(i);
//            FileDto fileDto = new FileDto();
//            BeanUtils.copyProperties(file, fileDto);
//            fileDtoList.add(fileDto);
//        }
        List<FileDto> fileDtoList = CopyUtil.copyList(fileList, FileDto.class);
        pageDto.setList(fileDtoList);

    }

    /**
     *保存
     * @param fileDto
     */
    public void save(FileDto fileDto) {
        File file = CopyUtil.copy(fileDto, File.class);
        if (StringUtils.isEmpty(fileDto.getId())) {
            this.insert(file);
        } else {
            this.update(file);
        }

    }

    /**
     * 新增
     * @param file
     */
    private void insert(File file) {

        Date now = new Date();

                    file.setCreatedAt(now);
                    file.setUpdatedAt(now);
        file.setId(UuidUtil.getShortUuid());

        fileMapper.insert(file);

    }


    /**
     * 更新
     * @param file
     */
    private void update(File file) {
                    file.setUpdatedAt(new Date());
        fileMapper.updateByPrimaryKey(file);

    }

    /**
     * 根据ID删除
     * @param id
     */
    public void delete(String id) {
        fileMapper.deleteByPrimaryKey(id);

    }


}
