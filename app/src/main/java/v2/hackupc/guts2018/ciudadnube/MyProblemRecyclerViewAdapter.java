package v2.hackupc.guts2018.ciudadnube;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import v2.hackupc.guts2018.ciudadnube.Objects.Problem;
import v2.hackupc.guts2018.ciudadnube.ProblemListFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Problem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyProblemRecyclerViewAdapter extends RecyclerView.Adapter<MyProblemRecyclerViewAdapter.ViewHolder> {

    private final List<Problem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyProblemRecyclerViewAdapter(List<Problem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_problem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mDescription.setText(mValues.get(position).getDescription());
        Picasso.get().load(mValues.get(position).getImageUrl()).fit()
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(R.drawable.ic_broken_image)
                .into(holder.mImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mDescription;
        public final ImageView mImage;
        public Problem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mDescription = view.findViewById(R.id.description);
            mImage = view.findViewById(R.id.image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescription.getText() + "'";
        }
    }
}
