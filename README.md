Prueba técnica Capitole.

En esta solución he utilizado una arquitectura hexagonal, la cual permite una separación clara entre las diferentes capas de la aplicación y facilita el mantenimiento y la evolución de la misma. Sin embargo, asumí un fallo de diseño al acoplar el framework a la capa de aplicación. En futuras implementaciones, debería evitar esto y utilizar un component scan para buscar solo los handlers y ponerlos en el contexto de Spring.

Además, he aplicado el patrón CQRS, el cual permite separar la lógica de escritura de la de lectura en la aplicación. En este ejemplo, he utilizado una query, pero lo ideal sería utilizar un bus interno para la publicación de los queries y otro para los comandos. Un servidor Axon proporciona todo este control.

Todos los componentes de la aplicación tienen pruebas unitarias. Además he realizado las pruebas de integración que se solicitaban en ProductPriceControllerIntegrationTest.

También he añadido la librería Spotless para garantizar un formato de código consistente con los estándares de Google.

La solución utiliza las últimas versiones de todas las herramientas y tecnologías involucradas.

Un saludo,
Jorge Martinez.
