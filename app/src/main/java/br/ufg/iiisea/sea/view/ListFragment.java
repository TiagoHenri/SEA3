package br.ufg.iiisea.sea.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.ufg.iiisea.sea.R;

/**
 * Created by w8.1 on 28/08/2016.
 */
public class ListFragment extends Fragment {

    private ListView lstTasks = null;

    public ListFragment(){
        // Required empty public constructor
    }

    public static ListFragment newInstance(int index){
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View convertView = inflater.inflate(R.layout.fragment_list, container, false);
        lstTasks = (ListView) convertView.findViewById(R.id.lstFragmentTextNotes);

        /*ArrayAdapter<ObjectTask> ad = new AdapterFragmentTasks(convertView.getContext(),
                R.layout.adapter_fragment_tasks, InitialConfig.lista_tasks.get(getArguments().getInt("index")));
        lstTasks.setAdapter(ad);
        */

        lstTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(convertView.getContext(), EditTask.class);

                ObjectTask obj = InitialConfig.lista_tasks.get(getArguments().getInt("index")).get(position);
                int day = obj.getDayOfWeek();
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_WEEK, day);
                String header = InitialConfig.weekDayFormat.format(cal.getTime()).toUpperCase();
                header = header + " - " + InitialConfig.dayOfMonth[day-1];
                header = header + " - " + obj.getHour();

                intent.putExtra(InitialConfig.KEY_HEADER, header);
                intent.putExtra(InitialConfig.KEY_ID, obj.getId());
                intent.putExtra(InitialConfig.KEY_DAYOFWEEK, obj.getDayOfWeek());
                intent.putExtra(InitialConfig.KEY_HOUR, obj.getHour());
                intent.putExtra(InitialConfig.KEY_TASK, obj.getTask());
                intent.putExtra(InitialConfig.KEY_TYPE, obj.getType());

                startActivity(intent);
                */
            }
        });

        return convertView;
    }
}
