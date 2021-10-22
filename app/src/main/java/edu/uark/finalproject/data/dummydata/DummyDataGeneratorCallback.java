package edu.uark.finalproject.data.dummydata;

import java.util.List;

import edu.uark.finalproject.data.Message;

public interface DummyDataGeneratorCallback {

    void dummyDataCreated(List<Message> messages);
    void onDataNotCreated();
}