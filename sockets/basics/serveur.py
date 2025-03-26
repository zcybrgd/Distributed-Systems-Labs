import socket

# Création du socket serveur
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind(("0.0.0.0", 5000))
server_socket.listen(5) # taille max de la file d'attente des demandes

print("Serveur en attente de connexions...")
conn, addr = server_socket.accept()
print(f"Connexion acceptée depuis {addr}")

# Communication
data = conn.recv(1024).decode()
print(f"Reçu : {data}")
conn.send("Message bien reçu!".encode())

conn.close()
server_socket.close()
