import socket

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect(("127.0.0.1", 5000))

client_socket.send("Hello, Serveur!".encode())
response = client_socket.recv(1024).decode()

print(f"Réponse du serveur : {response}")
client_socket.close()
