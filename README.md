# Tarea 2
Máximo Ignacio Beltrán Aranzáez
 
Benjamín Antonio Poblete Castillo

## Cambios al UML

1. Se creó la clase Externo, la cual representa la gente ajena a la empresa que se puede invitar
2. Se cámbio la interfaz Invitable, ahora el método invitar requiere una reunion a la cual se invita
3. Se creó un getter y un setter para manejar las notas a reunión, el setter solo permite agregar notas.
4. Se agregaron métodos para el manejo de asistencias a la reunion tales como crearInvitacion, el cual se usa internamente para la interfaz Invitable, verificarInvitacion, el cual permite comprobar si un invitable está en efecto invitado a la reunión y asistirInvitable, el cual actualiza el registro de asistencias.
5. Se implemento la creación de informes de una Reunion, la cual aprovecha la implementación del método toString y guarda el resultado en un archivo de texto.
6. Los métodos iniciar y finalizar ahora retornan el momento en el cual se llaman, esto es para fines de testing, pero no afectan la funcionalidad en lo absoluto.
7. La clase Departamento ahora tiene un setter, el cual permite agregar un empleado a la lista.
8. La clase Invitación ahora guarda la persona asociada a esta misma, esto es para fines de comprobar asistencia.
9. La clase Asistencia igualmente guarda la persona presente, esto no es estrictamente necesario, pero ayuda a tener claridad de quien vino a la reunión.
