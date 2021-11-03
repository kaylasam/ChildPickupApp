package edu.uark.finalproject.data.dummydata;

import java.util.List;

public interface DummyDataGeneratorCallback {

    void dummyDataCreated(List<Message> messages);
    void onDataNotCreated();
}