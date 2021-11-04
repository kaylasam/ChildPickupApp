package edu.uark.finalproject;

import android.content.Context;

import androidx.annotation.NonNull;

import edu.uark.finalproject.data.ChildPickupRepository;
import util.AppExecutors;

public class Injection {
    public static ChildPickupRepository provideDataRepository(AppExecutors executors, @NonNull Context context) {
        return ChildPickupRepository.getInstance(executors,context);
    }
}
