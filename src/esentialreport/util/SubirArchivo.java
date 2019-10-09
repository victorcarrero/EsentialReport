/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esentialreport.util;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author victor
 */
public class SubirArchivo {

    public static Map subir(HttpServletRequest request, String rutaArchivoDestino) throws FileUploadException, Exception {

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

// req es la HttpServletRequest que recibimos del formulario.
// Los items obtenidos serán cada uno de los campos del formulario,
// tanto campos normales como ficheros subidos.
        List items = upload.parseRequest(request);

// Se recorren todos los items, que son de tipo FileItem
        for (Object item : items) {
            FileItem uploaded = (FileItem) item;

            // Hay que comprobar si es un campo de formulario. Si no lo es, se guarda el fichero
            // subido donde nos interese
            if (!uploaded.isFormField()) {
                // No es campo de formulario, guardamos el fichero en algún sitio
                File fichero = new File("/archivos", uploaded.getName());
                uploaded.write(fichero);
            } else {
                // es un campo de formulario, podemos obtener clave y valor
                String key = uploaded.getFieldName();
                String valor = uploaded.getString();
            }
        }
        return null;

    }

}
