package com.twinflag.touch.service;

import com.twinflag.touch.entity.DataTableViewPage;
import com.twinflag.touch.model.Program;

import java.io.IOException;

public interface ProgramService {

    /**
     * 保存或者修改节目
     * @param program
     */
    void saveProgram(String programName, String program) throws IOException;

    /**
     * 删除节目
     * @param id
     */
    void deleteProgram(Integer id);

    void updateProgram(Integer id, String programContent) throws IOException;

    /**
     * 查找所有的节目
     */
    void findAllProgram();

    /**
     * 发布节目
     */
    void publishProgram(Program program);

    /**
     * 停止节目
     * @param program
     */
    void stopProgram(Program program);

    Program findProgram(Integer id);

    boolean isProgramNameExist(String programName);

    DataTableViewPage<Program> findAll(int page, int pageSize);
}
