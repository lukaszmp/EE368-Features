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

import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.Arrays;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.serotonin.mango.view.export.CsvWriter;
import com.serotonin.mango.view.text.TextRenderer;
import com.serotonin.web.i18n.I18NUtils;

/**
 * @author Matthew Lohbihler
 */
public class ReportCsvStreamer implements ReportDataStreamHandler {
    private final PrintWriter out;

    // Working fields
    private TextRenderer textRenderer;
    private final DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
    private CsvWriter csvWriter = new CsvWriter();
    //team created variables:
    private String lastUsedSensor = null;
    private int numSensors = 0;
    private final ResourceBundle bundle;
    private String storeData[][] = new String[20000][60]; //pre-set to hold 20000 lines of data for 12 sensors
    private int rowNum = 1; // 0 index is for headers
    private int totalRows = 1;

    public ReportCsvStreamer(PrintWriter out, ResourceBundle bundle) {
        this.bundle = bundle;
        this.out = out;
        
        // Write the headers.
        //save to double array
        storeData[0][0] = I18NUtils.getMessage(bundle, "reports.pointName");
        storeData[0][1] = I18NUtils.getMessage(bundle, "common.time");
        storeData[0][2] = I18NUtils.getMessage(bundle, "common.value");
        storeData[0][3] = I18NUtils.getMessage(bundle, "reports.rendered");
        storeData[0][4] = I18NUtils.getMessage(bundle, "common.annotation");
    }

    public void startPoint(ReportPointInfo pointInfo) {
        String name = pointInfo.getExtendedName();
        if (lastUsedSensor == null){
            lastUsedSensor = name;
        }
        if (name != lastUsedSensor && name != null && name != "") { // if next datapoint is using a different sensor:
            numSensors += 5;
            rowNum = 1;
            lastUsedSensor = name;
            //write headers to new columns
            //save to storeData
            storeData[0][numSensors + 0] = I18NUtils.getMessage(bundle, "reports.pointName");
            storeData[0][numSensors + 1] = I18NUtils.getMessage(bundle, "common.time");
            storeData[0][numSensors + 2] = I18NUtils.getMessage(bundle, "common.value");
            storeData[0][numSensors + 3] = I18NUtils.getMessage(bundle, "reports.rendered");
            storeData[0][numSensors + 4] = I18NUtils.getMessage(bundle, "common.annotation");
        }
        storeData[rowNum][numSensors] = name;
        textRenderer = pointInfo.getTextRenderer();
    }

    public void pointData(ReportDataValue rdv) {
        storeData[rowNum][numSensors] = lastUsedSensor;

        storeData[rowNum][numSensors + 1] = dtf.print(new DateTime(rdv.getTime()));

        if (rdv.getValue() == null) {
            storeData[rowNum][numSensors + 2] = storeData[rowNum][numSensors + 3] = null;
        }
        else {
            storeData[rowNum][numSensors + 2] = rdv.getValue().toString();
            storeData[rowNum][numSensors + 3] = textRenderer.getText(rdv.getValue(), TextRenderer.HINT_FULL);
        }
        storeData[rowNum][numSensors + 4] = rdv.getAnnotation();
        rowNum++;
        if(rowNum > totalRows){
            totalRows = rowNum;
        }
    }

    public void done() {
        //run through all data and print to excel one row at a time
        for (int i = 0; i <= totalRows; i++){ //scroll through each row of data
            out.write(csvWriter.encodeRow(storeData[i])); //format and write entire row of data
        }
        out.flush();
        out.close();
    }
}
