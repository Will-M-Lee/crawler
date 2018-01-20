package com.itacasa.cloud.service.crawler2.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author will
 */
public abstract class BaseService<Entity, Repository extends JpaRepository<Entity, String>>
{
    @Autowired
    protected Repository repository;

}
