package cl.exec.android.dev.exec.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cl.exec.android.dev.exec.R;
import cl.exec.android.dev.exec.classes.Mantis;

/**
 * Created by fran on 04-06-15.
 */
public class ListMantisAdapter extends BaseAdapter{


    Context context;
    protected List<Mantis> listMantis;
    LayoutInflater layoutInflater;

    /**
     * @param context set context, this, or getActivity
     * @param listMantis Array, ArrayAdapter, etc..
     */

    public ListMantisAdapter(Context context, List<Mantis> listMantis){
        this.listMantis = listMantis;
        this.layoutInflater = layoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listMantis.size();
    }

    @Override
    public Object getItem(int position) {
        return listMantis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listMantis.get(position).getIncidenceId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView != null){
            viewHolder = new ViewHolder();

            convertView = this.layoutInflater.inflate(R.layout.layout_list_item,parent,false);

            viewHolder.textViewIdProjectIncidence = (TextView) convertView.findViewById(R.id.textViewIdProjectIncidence);
            viewHolder.textViewProjectName = (TextView) convertView.findViewById(R.id.textViewProjectName);
            viewHolder.textViewIncidenceSeverity = (TextView) convertView.findViewById(R.id.textViewIncidenceSeverity);
            viewHolder.textViewIncidenceState = (TextView) convertView.findViewById(R.id.textViewIncidenceState);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Mantis mantis = listMantis.get(position);

        viewHolder.textViewIdProjectIncidence.setText(mantis.getIncidenceId());
        viewHolder.textViewProjectName.setText(mantis.getProjectId());
        viewHolder.textViewIncidenceSeverity.setText(mantis.getIncidenceSeverity());
        viewHolder.textViewIncidenceState.setText(mantis.getIncidenceState());

        return convertView;
    }

    private class ViewHolder{

        TextView textViewIdProjectIncidence;
        TextView textViewProjectName;
        TextView textViewIncidenceSeverity;
        TextView textViewIncidenceState;

    }
}
