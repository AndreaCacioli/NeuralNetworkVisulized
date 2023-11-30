import matplotlib.pyplot as plt
import numpy as np
import torch

SPREAD = 0.1
BUMPS_HEIGHT = 5


# Define the function
def f(x, y):
    return BUMPS_HEIGHT * (np.sin(x) * np.cos(y)) + SPREAD * (x**2 + y**2)


def f_t(x_tensor):
    sincos = torch.sin(x_tensor[0]) * torch.cos(x_tensor[1])
    sincos = sincos * BUMPS_HEIGHT
    square = torch.square(x_tensor)
    square = square[0] + square[1]
    square = square * SPREAD
    ret = sincos + square
    return ret


# Create a 2D array of grid points
x = np.linspace(-10, 10, 100)
y = np.linspace(-10, 10, 100)

# Calculate the elevation data
X, Y = np.meshgrid(x, y)
Z = f(X, Y)

# Create a 3D plot
fig = plt.figure(figsize=(10, 6))
ax = fig.add_subplot(projection="3d", computed_zorder=False)

# Plot the surface
surf = ax.plot_surface(X, Y, Z, cmap="gist_earth")

surf.shading = "gouraud"

# Set the plot limits
ax.set_xlim(-10, 10)
ax.set_ylim(-10, 10)
ax.set_zlim(0, 100)

# Set the labels
ax.set_xlabel("X")
ax.set_ylabel("Y")
ax.set_zlabel("Z")

# Set the title
ax.set_title("Graph of a weird loss function")

device = torch.device("cpu")

p = torch.tensor([9.5, -8.0], device=device, requires_grad=True)
p = torch.randn(2, device=device, requires_grad=False)
p = p * 9.5
p.requires_grad_(True)


ps = []
optim = torch.optim.Adam([p], lr=0.05)
epochs = 1000

for epoch in range(epochs):
    optim.zero_grad()
    y_p = f_t(x_tensor=p)
    ps.append(
        (p.detach().numpy()[0], p.detach().numpy()[1], y_p.item())
    )  # Just for plotting
    y_p.backward()
    optim.step()

# TODO plot the ps
for point in ps:
    ax.scatter(point[0], point[1], point[2], s=5, zorder=10, c="red")
# View the plot
plt.show()
