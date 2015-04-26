package com.szerletomasz.course.modernartui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MoreInfoDialog extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setMessage( R.string.dialog_message );
        builder.setPositiveButton( R.string.dialog_ok,
                new DialogInterface.OnClickListener() {

                    public void onClick ( DialogInterface dialog, int id ) {

                        Intent visit = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.moma.org") );
                        Intent chooser = Intent.createChooser( visit, getResources().getString( R.string.open_with ) );
                        startActivity( chooser );
                    }
                } );
        builder.setNegativeButton( R.string.dialog_cancel, null );

        return builder.create();
    };

}
