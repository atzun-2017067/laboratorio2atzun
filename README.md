# 📌 Sistema de Gestión de Usuarios (Java Swing)

## 🚀 Primer inicio del sistema

Al ejecutar el proyecto por **primera vez**, el sistema generará automáticamente un archivo llamado:

```
usuarios.txt
```

Este archivo se crea en la raíz del proyecto y contiene usuarios iniciales preconfigurados para facilitar las pruebas y el uso inmediato del sistema.

---

## 👥 Usuarios generados automáticamente

Se crearán los siguientes usuarios:

```
umg1,123,Usuario,Activo,1
umg2,123,Usuario,Activo,1
umg3,123,Usuario,Activo,1
umg4,123,Usuario,Activo,1
umg5,123,Administrador,Activo,1
```

---

## 🔐 Credenciales del administrador

Para acceder con privilegios de administrador:

* **Usuario:** `umg5`
* **Contraseña:** `123`
* **Rol:** Administrador

---

## 📋 Estructura del archivo `usuarios.txt`

Cada línea del archivo sigue el siguiente formato:

```
usuario,contraseña,rol,estado,sesion
```

### 🔎 Descripción de campos:

* **usuario:** nombre de acceso
* **contraseña:** clave del usuario
* **rol:** tipo de usuario (`Administrador` o `Usuario`)
* **estado:** `Activo` o `Inactivo`
* **sesion:**

  * `1` → debe cambiar contraseña en el primer inicio
  * `0` → ya actualizó su contraseña

---

## ⚠️ Importante

* En el **primer inicio de sesión**, todos los usuarios deberán cambiar su contraseña.
* El sistema no permitirá continuar hasta completar este proceso.
* El usuario administrador (`umg5`) tiene acceso a funcionalidades adicionales como mantenimiento de usuarios.

---

## 💡 Notas

* Si el archivo `usuarios.txt` ya existe, el sistema **no lo sobrescribirá**.
* Puedes eliminar el archivo para reiniciar los usuarios por defecto.

---

✨ Sistema desarrollado en Java Swing con manejo básico de archivos para fines educativos.
