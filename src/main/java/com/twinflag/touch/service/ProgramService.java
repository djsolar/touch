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
     * @param program
     */
    void deleteProgram(Program program);

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

    void findProgram(Program program);

    boolean isProgramNameExist(String programName);

    DataTableViewPage<Program> findAll(int page, int pageSize);
}
