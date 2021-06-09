package com.deu.cse.volt.Main.DetailThings;

import com.deu.cse.volt.Login.BearerTokenTemp;

public class ChartModelTemp {
    private static ChartModelTemp chart_Line = new ChartModelTemp();
    private String Line;
    private ChartModelTemp(){
    }

    public static ChartModelTemp getChart_Line() {
        return chart_Line;
    }

    public static void setChart_Line(ChartModelTemp chart_Line) {
        ChartModelTemp.chart_Line = chart_Line;
    }

    public String getLine() {
        return Line;
    }

    public void setLine(String line) {
        Line = line;
    }
}
