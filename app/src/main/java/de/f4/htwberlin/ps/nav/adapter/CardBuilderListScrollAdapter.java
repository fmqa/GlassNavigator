package de.f4.htwberlin.ps.nav.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fadimk on 18.01.15.
 */
public class CardBuilderListScrollAdapter extends CardScrollAdapter {
    public CardBuilderListScrollAdapter(List<? extends CardBuilder> children) {
        this.children = children;
    }

    public CardBuilderListScrollAdapter(Iterable<? extends CardBuilder> iterable) {
        ArrayList<CardBuilder> list = new ArrayList<>();
        for (CardBuilder child : iterable) {
            list.add(child);
        }
        children = list;
    }

    public CardBuilderListScrollAdapter(CardBuilder[] array) {
        this(Arrays.asList(array));
    }

    @Override
    public int getCount() {
        return children.size();
    }

    @Override
    public Object getItem(int index) {
        return children.get(index);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return children.get(i).getView(view, viewGroup);
    }

    @Override
    public int getPosition(Object item) {
        int result = children.indexOf(item);
        if (result == -1) {
            return CardScrollView.INVALID_POSITION;
        }
        return result;
    }

    private List<? extends CardBuilder> children;
}
