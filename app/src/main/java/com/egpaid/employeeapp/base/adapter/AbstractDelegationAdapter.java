package com.egpaid.employeeapp.base.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractDelegationAdapter<T> extends RecyclerView.Adapter {

    protected AdapterDelegatesManager<T> delegatesManager;
    protected List<T> items;

    public AbstractDelegationAdapter() {
        this(new AdapterDelegatesManager<T>());
    }

    public AbstractDelegationAdapter(@NonNull AdapterDelegatesManager<T> delegatesManager) {
        this.delegatesManager = delegatesManager;
        items = Collections.emptyList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(items, position, holder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        delegatesManager.onBindViewHolder(items, position, holder, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Get the items / data source of this adapter
     *
     * @return The items / data source
     */
    @NonNull
    public List<T> getItems() {
        return items;
    }

    /**
     * Set the items / data source of this adapter
     *
     * @param items The items / data source
     */
    public <U extends T> void setItems(@NonNull List<U> items) {
        this.items = new ArrayList<T>(items);
    }

    /**
     * Getter for delegates manager
     *
     * @return delegatesManager The AdapterDelegatesManager instance
     */
    protected final AdapterDelegatesManager<T> getDelegatesManager() {
        return delegatesManager;
    }
}
