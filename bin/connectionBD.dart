import 'package:postgres/postgres.dart';

class ConnectionBD {
  // Método para obtener la conexión
  static Future<PostgreSQLConnection> connect() async {
    var connection = PostgreSQLConnection(
      'database-1.c3mas0eeazuj.us-east-2.rds.amazonaws.com',
      5432,
      'BDevent',
      username: 'postgres',
      password: '12568658',
    );

    try {
      // Abre la conexión
      await connection.open();
      print('Conexión exitosa a PostgreSQL');
    } catch (e) {
      print('Error al conectar a la base de datos: $e');
    }

    return connection;
  }

  // Método para cerrar la conexión
  static Future<void> closeConnection(PostgreSQLConnection connection) async {
    await connection.close();
    print('Conexión cerrada');
  }
}
