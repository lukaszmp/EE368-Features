/*
    Mango - Open Source M2M - http://mango.serotoninsoftware.com
    Copyright (C) 2006-2011 Serotonin Software Technologies Inc.
    @author Matthew Lohbihler
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.serotonin.mango.vo.report;

import com.serotonin.mango.rt.dataImage.types.MangoValue;
import com.serotonin.mango.view.text.TextRenderer;

/**
 * @author Matthew Lohbihler
 */
public class ReportPointInfo {
    private int reportPointId;
    private String deviceName;
    private String pointName;
    private int dataType;
    private MangoValue startValue;
    private TextRenderer textRenderer;
    private String colour;
    private boolean consolidatedChart;

    // Team added variables:
    private boolean lineChart;  //not sure wether to check radio buttons w/ bools?
    private boolean scatterChart;
    private String chartTitle;
    private String XAxisLabel;
    private String YAxisLabel;
    private String YReferenceLine;
    //each variable will most likely need getter and setter functions

    public String getExtendedName() {
        return deviceName + " - " + pointName;
    }

    public int getReportPointId() {
        return reportPointId;
    }

    public void setReportPointId(int reportPointId) {
        this.reportPointId = reportPointId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public MangoValue getStartValue() {
        return startValue;
    }

    public void setStartValue(MangoValue startValue) {
        this.startValue = startValue;
    }

    public TextRenderer getTextRenderer() {
        return textRenderer;
    }

    public void setTextRenderer(TextRenderer textRenderer) {
        this.textRenderer = textRenderer;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean isConsolidatedChart() {
        return consolidatedChart;
    }

    public void setConsolidatedChart(boolean consolidatedChart) {
        this.consolidatedChart = consolidatedChart;
    }

    //Team added getter and setter functions:

    public boolean isLineChart() {return lineChart;}

    public void setLineChart(boolean lineChart) {this.lineChart = lineChart;}

    public boolean isScatterChart() {return scatterChart;}

    public void setScatterChart(boolean scatterChart) {this.scatterChart = scatterChart;}

    public String getXAxisLabel() {return XAxisLabel;}

    public void setXAxisLabel(String Xlabel) {this.XAxisLabel = Xlabel;}

    public String getYAxisLabel() {return YAxisLabel;}

    public void setYAxisLabel(String Ylabel) {this.YAxisLabel = Ylabel;}

    public String getChartTitle() {return chartTitle;}

    public void setChartTitle(String title) {this.chartTitle = title;}

    public String getYReferenceLine() {return YReferenceLine;}

    public void setYReferenceLine(String line) {this.YReferenceLine = line;}

}
