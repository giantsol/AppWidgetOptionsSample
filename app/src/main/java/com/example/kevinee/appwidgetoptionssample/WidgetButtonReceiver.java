package com.example.kevinee.appwidgetoptionssample;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;
import static com.example.kevinee.appwidgetoptionssample.MyAppWidgetProvider.BUNDLE_KEY_WIDGET_EXPANDED;
import static com.example.kevinee.appwidgetoptionssample.MyAppWidgetProvider.BUNDLE_KEY_WIDGET_ID;

public class WidgetButtonReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int appWidgetId = intent.getIntExtra(BUNDLE_KEY_WIDGET_ID, INVALID_APPWIDGET_ID);
        boolean isWidgetExpanded = intent.getBooleanExtra(BUNDLE_KEY_WIDGET_EXPANDED, false);

        if (appWidgetId != INVALID_APPWIDGET_ID) {
            AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
            Bundle options = widgetManager.getAppWidgetOptions(appWidgetId);
            options.putBoolean(BUNDLE_KEY_WIDGET_EXPANDED, !isWidgetExpanded);
            widgetManager.updateAppWidgetOptions(appWidgetId, options);
        }
    }
}

