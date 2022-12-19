package com.automl.datarepresentation.bean;

import java.util.List;

public class ComboItem {
	private String key;
    private List<String> value;

    public ComboItem(String key, List<String> value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public List<String> getValue()
    {
        return value;
    }
}
