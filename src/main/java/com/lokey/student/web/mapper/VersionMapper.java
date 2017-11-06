package com.lokey.student.web.mapper;

import com.lokey.student.web.model.Version;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;



@Component(value = "versionMapper")
public interface VersionMapper {
    public int insertVersion(Version version);

    public List<Version> selectByPage(Map<String, Object> map);

    public void updateVersion(Version version);

    public Version selectNewVersion();
}

