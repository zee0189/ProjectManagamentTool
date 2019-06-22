/*******************************************************************************
 *  Copyright (c) 2019.
 *  This  code file/snippet/block, including any other configuration files,
 *  is for the sole use of the Evive Health, LLC and may contain business
 *  confidential and privileged information.
 *  Any unauthorized review, use, disclosure or distribution is prohibited.
 ******************************************************************************/

package com.project.ProjectMangementTool.Dao;

import com.project.ProjectMangementTool.Models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProjectRepo extends CrudRepository<Project, String> {

}
