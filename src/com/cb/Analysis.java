package com.cb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cb.utils.CommonUtils;
import com.cb.utils.GraphUtils;

public class Analysis {
    public static void divideGraphAndOrderByDc() {
        List<String> list = CommonUtils.getInputFile("");
        Map<String, List<String>> graph = GraphUtils.getGraph("");
        List<Set<String>> graphSets = new ArrayList<Set<String>>();
    }
}
