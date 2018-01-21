package com.example.kevinee.appwidgetoptionssample;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MyAppWidgetProvider extends AppWidgetProvider {
    public static final String BUNDLE_KEY_WIDGET_ID = "bundle_key_widget_id";
    public static final String BUNDLE_KEY_WIDGET_EXPANDED = "bundle_key_widget_expanded";
    public static final String ACTION_TOGGLE_EXPANSION = "com.example.APPWIDGET_TOGGLE_EXPANSION";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int id : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, id);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        updateAppWidget(context, appWidgetManager, appWidgetId);
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
        views.setTextViewText(R.id.widget_title, "Title");
        views.setTextViewText(R.id.widget_description, "Description");

        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
        boolean isWidgetExpanded = options.getBoolean(BUNDLE_KEY_WIDGET_EXPANDED, false);
        if (isWidgetExpanded) {
            views.setImageViewResource(R.id.widget_button, R.drawable.ic_arrow_drop_up_black_24dp);
            views.setViewVisibility(R.id.widget_description, VISIBLE);
        } else {
            views.setImageViewResource(R.id.widget_button, R.drawable.ic_arrow_drop_down_black_24dp);
            views.setViewVisibility(R.id.widget_description, INVISIBLE);
        }

        Intent intent = new Intent(ACTION_TOGGLE_EXPANSION);
        intent.setPackage(context.getPackageName());
        intent.putExtra(BUNDLE_KEY_WIDGET_ID, appWidgetId);
        intent.putExtra(BUNDLE_KEY_WIDGET_EXPANDED, isWidgetExpanded);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_button, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

}
