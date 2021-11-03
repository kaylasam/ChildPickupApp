package edu.uark.finalproject;

import android.content.Context;

import androidx.annotation.NonNull;

import util.AppExecutors;

public class Injection {
    public static MessagesRepository provideMessagesRepository(AppExecutors executors,@NonNull Context context) {
        return MessagesRepository.getInstance(executors,context);
    }
}
