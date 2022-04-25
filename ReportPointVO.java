package com.serotonin.mango.vo.report;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.serotonin.util.SerializationHelper;

public class ReportPointVO implements Serializable {
    private int pointId;
    private String colour;
    private boolean consolidatedChart;

    //team added variables:
    private String chartTitle;
    private String XAxisLabel;
    private String YAxisLabel;
    private String YReferenceLine;
    private boolean scatterChart;
    private boolean lineChart;
    //add getter and setter stuff

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
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

    //team added getter and setter stuff:
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
    //do Serialization stuff next

    //
    //
    // Serialization
    //
    private static final long serialVersionUID = -1;
    private static final int version = 2;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(version);

        out.writeInt(pointId);
        SerializationHelper.writeSafeUTF(out, colour);
        out.writeBoolean(consolidatedChart);
        //team added serialization stuffs
        SerializationHelper.writeSafeUTF(out, chartTitle);
        SerializationHelper.writeSafeUTF(out, XAxisLabel);
        SerializationHelper.writeSafeUTF(out, YAxisLabel);
        SerializationHelper.writeSafeUTF(out, YReferenceLine);
        out.writeBoolean(scatterChart);
        out.writeBoolean(lineChart);
        //end
    }

    private void readObject(ObjectInputStream in) throws IOException {
        int ver = in.readInt();

        // Switch on the version of the class so that version changes can be elegantly handled.
        if (ver == 1) {
            pointId = in.readInt();
            colour = SerializationHelper.readSafeUTF(in);
            consolidatedChart = true;

            //team added stuff
            chartTitle = SerializationHelper.readSafeUTF(in);
            XAxisLabel = SerializationHelper.readSafeUTF(in);
            YAxisLabel = SerializationHelper.readSafeUTF(in);
            YReferenceLine = SerializationHelper.readSafeUTF(in);
            lineChart = true;
            scatterChart = false;
            //end
        }
        else if (ver == 2) {
            pointId = in.readInt();
            colour = SerializationHelper.readSafeUTF(in);
            consolidatedChart = in.readBoolean();

            //team added stuff
            chartTitle = SerializationHelper.readSafeUTF(in);
            XAxisLabel = SerializationHelper.readSafeUTF(in);
            YAxisLabel = SerializationHelper.readSafeUTF(in);
            YReferenceLine = SerializationHelper.readSafeUTF(in);
            lineChart = in.readBoolean();
            scatterChart = in.readBoolean();
            //end

        }
    }
}
