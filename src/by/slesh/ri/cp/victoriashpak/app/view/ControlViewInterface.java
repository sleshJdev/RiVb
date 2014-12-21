package by.slesh.ri.cp.victoriashpak.app.view;

import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;

public interface ControlViewInterface {

    String ACTION_FILE_OPEN = "action_file_open";
    String ACTION_BIN_PERCENT = "action_binarization_percent";
    String ACTION_BIN_THRESHOLD = "action_binarization_threshold";
    String ACTION_SEGMENT_HISTOGRAM = "action_segment_histogram";
    String ACTION_TRIM = "action_trim";
    String ACTION_FIND_SYMBOL = "action_find_symbol";
    String ACTION_EXTRACT_AREA_INTEREST = "action_extract_area_interest";
    String ACTION_EXTRACT_FULLNAME = "action_extract_group_number";
    String ACTION_SEGMENT_FULLNAME = "action_segment_group_number";
    String ACTION_RECOGNIZE = "action_recognize_number";

    void addHistogramSegmentClickListener(ActionListener l);

    void addOpenFileClickListener(ActionListener l);

    void addBinPercentClickListener(ActionListener l);

    void addBinThresholdClickListener(ActionListener l);

    void enableControls();

    void addExtractAreaInterestClickListener(ActionListener l);

    void addTrimClickListener(ActionListener l);

    void addFindSymbolClickListener(ActionListener l);

    void addExtractFullNameClickListener(ActionListener l);

    void addSegmentFullNameClickListener(ActionListener l);

    void addRecognizeNumberClickListener(ActionListener l);
    
    void addPercentScrollListener(AdjustmentListener l);

    void addThresholdScrollListener(AdjustmentListener l);
    
    void updatePercentValue(int newValue);
    
    void updateThresholdValue(int newValue);
}
