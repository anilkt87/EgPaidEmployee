package com.egpaid.employeeapp.base.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * Usage: https://developer.android.com/reference/android/support/v7/util/DiffUtil.DiffResult.html
 */
public class DiffCallback extends DiffUtil.Callback {

    protected List oldList;
    protected List newList;

    @Inject
    public DiffCallback() {
    }

    public void setLists(@NonNull List oldList, @NonNull List newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    /**
     * Simply using toString for now for comparisons.
     * Should ideally be improved (or overridden) for better performance.
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return !(oldItemPosition >= oldList.size() || newItemPosition >= newList.size())
                && oldList.get(oldItemPosition).toString().equals(newList.get(newItemPosition).toString());
    }

    /**
     * Simply using toString for now for comparisons.
     * Should ideally be improved (or overridden) for better performance.
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return !(oldItemPosition >= oldList.size() || newItemPosition >= newList.size())
                && oldList.get(oldItemPosition).toString().equals(newList.get(newItemPosition).toString());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}