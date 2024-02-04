package com.answer.common.enums;

import lombok.Getter;

@Getter
public enum DataScope implements AnswerEnum {
    ALL(0, "全部权限"), CREATOR(1, "创建者权限");

    DataScope(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    private Integer id;
    private String content;
}