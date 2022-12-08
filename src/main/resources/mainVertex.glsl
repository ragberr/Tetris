#version 330 core

in vec3 position;
in vec3 color;

out vec3 passColor;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

void main() {
    float aspect = 1280/760;
    float border_width = 0.0000005;

    float maxX = 1.0 - border_width;
    float minX = border_width;
    float maxY = maxX / aspect;
    float minY = minX / aspect;

	gl_Position = projection * view * model * vec4(position, 1.0);

	if(position.x < maxX && position.x > minX && position.y < maxY && position.y > minY) {
	    passColor = vec3(0, 0, 0);
	}
	else {
	    passColor = color;
	}
}