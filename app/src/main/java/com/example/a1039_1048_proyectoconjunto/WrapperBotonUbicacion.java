package com.example.a1039_1048_proyectoconjunto;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.a1039_1048_proyectoconjunto.R;

public class WrapperBotonUbicacion extends ConstraintLayout {

    private TextView Alias;
    private TextView Toponimo;
    private TextView Coordenadas;

    private String strAlias;
    private String strToponimo;
    private String strCoordenadas;

    public WrapperBotonUbicacion(Context context) {
        this(context, null);
    }

    public WrapperBotonUbicacion(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.boton_ubicacion, this);

        this.Alias = view.findViewById(R.id.alias);
        this.Toponimo = view.findViewById(R.id.toponimo);
        this.Coordenadas = view.findViewById(R.id.coordenadas);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WrapperBotonUbicacion, 0, 0);
        try {
            strAlias = array.getString(R.styleable.WrapperBotonUbicacion_alias);
            strToponimo = array.getString(R.styleable.WrapperBotonUbicacion_toponimo);
            strCoordenadas = array.getString(R.styleable.WrapperBotonUbicacion_coordenadas);
        } finally {
            array.recycle();
        }


        Alias.setText(strAlias);
        Toponimo.setText(strToponimo);
        Coordenadas.setText(strCoordenadas);
    }

    public String getAlias() {
        return strAlias;
    }

    public String getToponimo() {
        return strToponimo;
    }

    public String getCoordenadas() {
        return strCoordenadas;
    }

    public void setAlias(String text) {
        this.strAlias = text;
        Alias.setText(strAlias);
        invalidate();
        requestLayout();
    }

    public void setToponimo(String text) {
        this.strToponimo = text;
        Toponimo.setText(strToponimo);
        invalidate();
        requestLayout();
    }

    public void setCoordenadas(String text) {
        this.strCoordenadas = text;
        Coordenadas.setText(strCoordenadas);
        invalidate();
        requestLayout();
    }
}
