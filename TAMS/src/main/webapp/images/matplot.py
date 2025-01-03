import numpy as np
import matplotlib.pyplot as plt
omega_c =2*np.pi
N
10000
A = np.random.uniform(1, 3, N)
theta = np.random.uniform(0, 2 * np.pi,
t_values = np.linspace(0, 10, 100)
N)
def xt(t):
return A * np.cos(omega_c
k
t +
theta)
mean_Xt = [np.mean(X_t(t)) for t in t_values]
t1- 1
RX = [np.mean(X_t(t1)
X_t(t2)) for t2 in t_values]
plt.figure(figsize=(12, 6))
plt.subplot(2, 1, 1)
plt.plot(t_values, mean_Xt, marker='o', label='Mean of X(t)")
plt.axhline(y=6, color='r', linestyle='--`, label=`Zero Mean (Stationary)
plt,title('Mean of X(t) vs Time")
plt.xlabel('Time (t)")
plt.ylabel( 'Mean')
plt.legend()
plt.grid(True)
plt.grid(True)
plt.subplot(2, 1, 2)
plt.plot(t_values, R_X, marker='o', label=f'R_X(ti=(t1), t2)')
plt.title('Autocorrelation R_X(t1, t2) vs t2")
plt.xlabel('Time t2')
plt.ylabel('Autocorrelation')
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()

