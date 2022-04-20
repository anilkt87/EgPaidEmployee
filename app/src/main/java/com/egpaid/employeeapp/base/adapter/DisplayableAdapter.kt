package com.egpaid.employeeapp.base.adapter

import android.os.Handler
import androidx.recyclerview.widget.DiffUtil
import com.egpaid.employeeapp.base.widget.DisplayableItem
import java.util.*

abstract class DisplayableAdapter(
        private val diffCallback: DiffCallback
) : AbstractDelegationAdapter<DisplayableItem>() {

    init {
        items = emptyList()
    }

    /**
     * Set or replace displayable items in the list.
     *
     * @param displayableItems List of displayable items.
     */
    override fun <T : DisplayableItem> setItems(displayableItems: List<T>) {
        val list = ArrayList<DisplayableItem>(displayableItems)
        calculateDiff(list)
    }

    /**
     * Similar to setItems() but adds a bit of async'ness
     *
     * @param displayableItems List of displayable items.
     */
    fun <T : DisplayableItem> setItemsAsync(displayableItems: List<T>) {
        val list = ArrayList<DisplayableItem>(displayableItems)
        calculateDiffAsync(list)
    }

    /**
     * Add a displayable item.
     *
     * @param displayableItem Item which needs to be added.
     */
    fun <T : DisplayableItem> addItem(displayableItem: T) {
        val list = ArrayList(items)
        list.add(displayableItem)
        calculateDiff(list)
    }

    /**
     * Add a displayable item at a particular position.
     *
     * @param displayableItem Item which needs to be added.
     * @param position        Position at which the item should be added.
     */
    fun <T : DisplayableItem> addItem(displayableItem: T, position: Int) {
        val list = ArrayList(items)
        list.add(position, displayableItem)
        calculateDiff(list)
    }

    /**
     *  Same as setItemsAsync but for adding single item
     */
    fun <T : DisplayableItem> addItemAsync(displayableItem: T, position: Int) {
        val list = ArrayList(items)
        list.add(position, displayableItem)
        calculateDiffAsync(list)
    }

    /**
     * Add all the displayable items to the list.
     *
     * @param displayableItems List of displayable items.
     */
    fun <T : DisplayableItem> addItems(displayableItems: List<T>) {
        val list = ArrayList(items)
        list.addAll(displayableItems)
        calculateDiff(list)
    }

    /**
     * Add all the displayable items to the list.
     *
     * @param displayableItems List of displayable items.
     * @param position         Position at which the items should be added.
     */
    fun <T : DisplayableItem> addItems(displayableItems: List<T>, position: Int) {
        val list = ArrayList(items)
        list.addAll(position, displayableItems)
        calculateDiff(list)
    }

    /**
     * Get the first position of a given item type, or the start position of the list.
     * @param type the item type to check.
     */
    fun <T : DisplayableItem> getFirstPositionOfTypeOrStart(type: Class<T>): Int {
        var position = items.indexOfFirst { type.isAssignableFrom(it.javaClass) }
        if (position == -1) { // Start if item type not found.
            position = 0
        }
        return position
    }

    /**
     * Get the last position of a given item type, or the end position of the list.
     * @param type the item type to check.
     */
    fun <T : DisplayableItem> getLastPositionOfTypeOrEnd(type: Class<T>): Int {
        var position = items.indexOfLast { type.isAssignableFrom(it.javaClass) } + 1
        if (position == 0) { // End if item type not found.
            position = items.size
        }
        return position
    }

    /**
     * Get the last position of a given item type, or the start position of the list.
     * @param type the item type to check.
     */

    fun <T : DisplayableItem> getLastPositionOfTypeOrStart(type: Class<T>): Int {
        return items.indexOfLast { type.isAssignableFrom(it.javaClass) }
    }

    /**
     * Add the displayable item to the list before the first of the given item type.
     *
     * @param displayableItem the displayable item.
     * @param type             the item type to add the item before.
     * @implNote inserts item at the start if item type not found.
     */
    fun <U : DisplayableItem, T : DisplayableItem> addItemBeforeFirstOfItemType(
            displayableItem: U, type: Class<T>) {
        addItem(displayableItem, getFirstPositionOfTypeOrStart(type))
    }

    /**
     * Add all the displayable items to the list before the first of the given item type.
     *
     * @param displayableItems List of displayable items.
     * @param type             the item type to add items before.
     * @implNote inserts items at the start if item type not found.
     */
    fun <U : DisplayableItem, T : DisplayableItem> addItemsBeforeFirstOfItemType(
            displayableItems: List<U>, type: Class<T>) {
        addItems(displayableItems, getFirstPositionOfTypeOrStart(type))
    }

    /**
     * Add the displayable item to the list after the last of the given item type.
     *
     * @param displayableItem the displayable item.
     * @param type             the item type to add the item after.
     * @implNote inserts item at the end if item type not found.
     */
    fun <U : DisplayableItem, T : DisplayableItem> addItemAfterLastOfItemType(
            displayableItem: U, type: Class<T>) {
        addItem(displayableItem, getLastPositionOfTypeOrEnd(type))
    }

    /**
     * Add the displayable item to the list after the last of the given item type.
     *
     * @param displayableItem the displayable item.
     * @param type             the item type to add the item after.
     * @implNote inserts item at the start if item type not found.
     */

    fun <U : DisplayableItem, T : DisplayableItem> addItemAfterLastOfItemTypeOrStart(
            displayableItem: U, type: Class<T>) {
        addItem(displayableItem, getLastPositionOfTypeOrStart(type) + 1)
    }

    /**
     * Add all the displayable items to the list after the last of the given item type.
     *
     * @param displayableItems List of displayable items.
     * @param type             the item type to add items after.
     * @implNote inserts items at the end if item type not found.
     */
    fun <U : DisplayableItem, T : DisplayableItem> addItemsAfterLastOfItemType(
            displayableItems: List<U>, type: Class<T>) {
        addItems(displayableItems, getLastPositionOfTypeOrEnd(type))
    }


    /**
     * Replace displayable item at a particular position.
     *
     * @param displayableItem Item which needs to be replaced.
     * @param position        Position at which the item should be replaced.
     */
    fun <T : DisplayableItem> replaceItem(displayableItem: T, position: Int) {
        val list = ArrayList(items)
        list[position] = displayableItem
        calculateDiff(list)
    }

    /**
     * Remove all items of a given type from the list.
     *
     * @param type the item type to remove.
     * @implNote uses isAssignableFrom as types may be AutoValue generated classes.
     */
    fun <T : DisplayableItem> removeAllOfItemType(type: Class<T>) {
        val list = ArrayList(items)
        items.filter { type.isAssignableFrom(it.javaClass) }
                .forEach { list.remove(it) }
        calculateDiff(list)
    }

    /**
     * Remove all items of a given type from the list if it's available.
     *
     * @param type the item type to remove.
     */
    fun <T : DisplayableItem> removeAllIfAvailable(type: Class<T>) {
        if (getItemCount(type) > 0) {
            removeAllOfItemType(type)
        }
    }

    /**
     * Get item count of a given type.
     *
     * @param type the item type to count.
     */
    fun <T : DisplayableItem> getItemCount(type: Class<T>): Int =
            items.count { type.isAssignableFrom(it.javaClass) }

    /**
     * Clears the list.
     */
    fun clearData() {
        calculateDiff(emptyList())
    }

    /**
     * TODO: Handle diff calculations on a separate thread.
     * https://medium.com/@jonfhancock/get-threading-right-with-diffutil-423378e126d2
     * https://hellsoft.se/a-nice-combination-of-rxjava-and-diffutil-fe3807186012
     */
    private fun calculateDiff(displayableItems: List<DisplayableItem>) {
        diffCallback.setLists(items, displayableItems)
        val result = DiffUtil.calculateDiff(diffCallback)
        items = displayableItems
        result.dispatchUpdatesTo(this)
    }

    /**
     * Similar to calculateDiff() but adds a bit of async'ness
     * Note - the code for difference calculation is still run in the main UI thread.
     */
    private fun calculateDiffAsync(displayableItems: List<DisplayableItem>) {
        Handler().post { calculateDiff(displayableItems) }
    }
}
