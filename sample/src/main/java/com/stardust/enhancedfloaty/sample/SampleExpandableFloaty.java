package com.stardust.enhancedfloaty.sample;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.stardust.enhancedfloaty.FloatyService;
import com.stardust.enhancedfloaty.R;
import com.stardust.enhancedfloaty.ResizableExpandableFloaty;
import com.stardust.enhancedfloaty.ResizableExpandableFloatyWindow;

/**
 * Created by Stardust on 2017/4/20.
 */

public class SampleExpandableFloaty extends ResizableExpandableFloaty.AbstractResizableExpandableFloaty {

    private ContextWrapper mContextWrapper;
    private View mResizer, mMoveCursor;

    public SampleExpandableFloaty() {
        setShouldRequestFocusWhenExpand(false);
    }

    @Override
    public View inflateCollapsedView(FloatyService service, ResizableExpandableFloatyWindow window) {
        ensureContextWrapper(service);
        return View.inflate(mContextWrapper, R.layout.floating_window_collapsed, null);
    }

    private void ensureContextWrapper(Context context) {
        if (mContextWrapper == null) {
            mContextWrapper = new ContextThemeWrapper(context, R.style.AppTheme);
        }
    }

    @Override
    public View inflateExpandedView(FloatyService service, ResizableExpandableFloatyWindow window) {
        ensureContextWrapper(service);
        View view = View.inflate(mContextWrapper, R.layout.floating_window_expanded, null);
        setListeners(view, window);
        setUpEditText(view, window);
        return view;
    }

    private void setUpEditText(View view, final ResizableExpandableFloatyWindow window) {
        final EditText editText = view.findViewById(R.id.editText);
        editText.setOnClickListener(v -> {
            window.requestWindowFocus();
            editText.requestFocus();
        });
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                editText.setText("");
                window.disableWindowFocus();
                return true;
            }
            return false;
        });
    }

    private void setListeners(final View view, final ResizableExpandableFloatyWindow window) {
        view.findViewById(R.id.close).setOnClickListener(v -> window.close());
        view.findViewById(R.id.move_or_resize).setOnClickListener(v -> {
            if (mMoveCursor.getVisibility() == View.VISIBLE) {
                mMoveCursor.setVisibility(View.GONE);
                mResizer.setVisibility(View.GONE);
            } else {
                mMoveCursor.setVisibility(View.VISIBLE);
                mResizer.setVisibility(View.VISIBLE);
            }
        });
        view.findViewById(R.id.minimize).setOnClickListener(v -> window.collapse());

    }

    @Nullable
    @Override
    public View getResizerView(View expandedView) {
        mResizer = expandedView.findViewById(R.id.resizer);
        return mResizer;
    }

    @Nullable
    @Override
    public View getMoveCursorView(View expandedView) {
        mMoveCursor = expandedView.findViewById(R.id.move_cursor);
        return mMoveCursor;
    }
}
