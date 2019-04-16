package com.gsp.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 菜单表Vo
 * Created by lcy on 2018/5/19.
 */
@Data
@ToString
@NoArgsConstructor
public class GspMenuVo {
    private String id;
    private String text;
    private Map<String, String> attributes;

    private Collection<GspMenuVo> children;

    public GspMenuVo(String id, String text) {
        this.id = id;
        this.text = text;
    }
}
