package br.com.jmsstudio.agenda.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * Created by jms on 23/12/16.
 */
public class ImageHelper {

    /**
     * Auxilida o carregamento de uma imagem <code>Bitmap</code> em uma <code>ImageView</code>
     *
     * @param imagePath
     * @param width
     * @param height
     * @param imageView
     * @param setTag
     */
    public static void loadImageIntoView(String imagePath, int width, int height, ImageView imageView, boolean setTag) {
        if (imagePath != null) {
            Bitmap imagem = BitmapFactory.decodeFile(imagePath);
            Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, width, height, true);
            imageView.setImageBitmap(imagemReduzida);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            if (setTag) {
                imageView.setTag(imagePath);
            }
        }
    }

}
