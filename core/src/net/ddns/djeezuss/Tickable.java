package net.ddns.djeezuss;

public interface Tickable
{
	void update(long delta);

	void draw();
	void dispose();
}
