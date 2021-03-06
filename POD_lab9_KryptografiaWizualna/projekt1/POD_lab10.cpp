
#include "pch.h"
#include <SFML/Graphics.hpp>
#include<SFML/System.hpp>
#include<SFML/Window.hpp>
#include <iostream>
#include <sstream>
#include <ctime>
#include <cstdlib>
#include <String>

std::string toString(int integer)
{
	std::ostringstream os;
	os << integer;
	return os.str();
}


int main()
{

	srand(time(NULL));

	sf::Image obraz;

	obraz.loadFromFile("in0.png");

	
	sf::Vector2u rozmiar = obraz.getSize();

	sf::Image zaszyfrowany_obraz1;
	zaszyfrowany_obraz1.create(rozmiar.x * 2, rozmiar.y * 2, sf::Color::Black);

	sf::Image zaszyfrowany_obraz2;
	zaszyfrowany_obraz2.create(rozmiar.x * 2, rozmiar.y * 2, sf::Color::Black);



	sf::Color kolor = sf::Color();

	for (int i = 0; i < rozmiar.x; i++)
	{
		for (int j = 0; j < rozmiar.y; j++)
		{
			int r = rand() % 2;
			kolor = obraz.getPixel(i, j);
			if ((kolor == sf::Color::White))
			{
				if (r)
				{
					zaszyfrowany_obraz1.setPixel(i * 2, j * 2, sf::Color::Black);
					zaszyfrowany_obraz1.setPixel(i * 2, j * 2 + 1, sf::Color::Black);
					zaszyfrowany_obraz1.setPixel(i * 2 + 1, j * 2, sf::Color::Transparent);
					zaszyfrowany_obraz1.setPixel(i * 2 + 1, j * 2 + 1, sf::Color::Transparent);

					zaszyfrowany_obraz2.setPixel(i * 2, j * 2, sf::Color::Black);
					zaszyfrowany_obraz2.setPixel(i * 2, j * 2 + 1, sf::Color::Black);
					zaszyfrowany_obraz2.setPixel(i * 2 + 1, j * 2, sf::Color::Transparent);
					zaszyfrowany_obraz2.setPixel(i * 2 + 1, j * 2 + 1, sf::Color::Transparent);
				}
				else
				{
					zaszyfrowany_obraz1.setPixel(i * 2, j * 2, sf::Color::Transparent);
					zaszyfrowany_obraz1.setPixel(i * 2, j * 2 + 1, sf::Color::Transparent);
					zaszyfrowany_obraz1.setPixel(i * 2 + 1, j * 2, sf::Color::Black);
					zaszyfrowany_obraz1.setPixel(i * 2 + 1, j * 2 + 1, sf::Color::Black);

					zaszyfrowany_obraz2.setPixel(i * 2, j * 2, sf::Color::Transparent);
					zaszyfrowany_obraz2.setPixel(i * 2, j * 2 + 1, sf::Color::Transparent);
					zaszyfrowany_obraz2.setPixel(i * 2 + 1, j * 2, sf::Color::Black);
					zaszyfrowany_obraz2.setPixel(i * 2 + 1, j * 2 + 1, sf::Color::Black);
				}


			}
			else
			{
				if (r)
				{
					zaszyfrowany_obraz1.setPixel(i * 2, j * 2, sf::Color::Black);
					zaszyfrowany_obraz1.setPixel(i * 2, j * 2 + 1, sf::Color::Black);
					zaszyfrowany_obraz1.setPixel(i * 2 + 1, j * 2, sf::Color::Transparent);
					zaszyfrowany_obraz1.setPixel(i * 2 + 1, j * 2 + 1, sf::Color::Transparent);

					zaszyfrowany_obraz2.setPixel(i * 2, j * 2, sf::Color::Transparent);
					zaszyfrowany_obraz2.setPixel(i * 2, j * 2 + 1, sf::Color::Transparent);
					zaszyfrowany_obraz2.setPixel(i * 2 + 1, j * 2, sf::Color::Black);
					zaszyfrowany_obraz2.setPixel(i * 2 + 1, j * 2 + 1, sf::Color::Black);
				}
				else
				{
					zaszyfrowany_obraz1.setPixel(i * 2, j * 2, sf::Color::Transparent);
					zaszyfrowany_obraz1.setPixel(i * 2, j * 2 + 1, sf::Color::Transparent);
					zaszyfrowany_obraz1.setPixel(i * 2 + 1, j * 2, sf::Color::Black);
					zaszyfrowany_obraz1.setPixel(i * 2 + 1, j * 2 + 1, sf::Color::Black);

					zaszyfrowany_obraz2.setPixel(i * 2, j * 2, sf::Color::Black);
					zaszyfrowany_obraz2.setPixel(i * 2, j * 2 + 1, sf::Color::Black);
					zaszyfrowany_obraz2.setPixel(i * 2 + 1, j * 2, sf::Color::Transparent);
					zaszyfrowany_obraz2.setPixel(i * 2 + 1, j * 2 + 1, sf::Color::Transparent);

				}
			}
		}
	}
	zaszyfrowany_obraz1.saveToFile("out0.png");
	zaszyfrowany_obraz2.saveToFile("out1.png");


	sf::RenderWindow window(sf::VideoMode::getDesktopMode(), "Kryptografia wizualna");
	window.setFramerateLimit(60);

	sf::RectangleShape tlo(sf::Vector2f(rozmiar.x * 4 + 20, rozmiar.y * 2));

	tlo.setFillColor(sf::Color::White);
	tlo.setPosition(0, rozmiar.y + 10);


	sf::Texture tekstury[3];
	tekstury[0].loadFromImage(obraz);
	tekstury[1].loadFromImage(zaszyfrowany_obraz1);
	tekstury[2].loadFromImage(zaszyfrowany_obraz2);

	sf::Sprite sprite[3];
	sprite[0].setTexture(tekstury[0]);
	sprite[1].setTexture(tekstury[1]);
	sprite[1].setPosition(0, rozmiar.y + 10);
	sprite[2].setTexture(tekstury[2]);
	sprite[2].setPosition(rozmiar.x * 2 + 20, rozmiar.y + 10);

	bool keyPressed = 0;
	bool scrolled = 0;

	sf::Font font;
	sf::Text text;
	font.loadFromFile("palai.ttf");
	text.setFont(font);

	text.setCharacterSize(20);
	text.setFillColor(sf::Color::White);
	text.setPosition(rozmiar.x + 30, 0);

	sf::Text text2;
	text2.setFont(font);

	text2.setCharacterSize(20);
	text2.setFillColor(sf::Color::White);
	text2.setPosition( -120, 0);
	text2.setString("Left -1px \nRight +1px \nA -10px \nD +10px \nZ -100px \nC +100px \n+ Zoom in \n- Zoom out");


	sf::View view(sf::Vector2f(rozmiar.x, rozmiar.y + 10 + rozmiar.y), sf::Vector2f(window.getSize().x, window.getSize().y));
	window.setView(view);



	while (window.isOpen())
	{
		if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left))
		{
			if (!keyPressed)
			{
				sprite[2].move(sf::Vector2f(-1, 0));
				keyPressed = 1;
			}
		}
		else if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right))
		{
			if (!keyPressed)
			{
				sprite[2].move(sf::Vector2f(1, 0));
				keyPressed = 1;
			}
		}
		else if (sf::Keyboard::isKeyPressed(sf::Keyboard::A))
		{
			if (!keyPressed)
			{
				sprite[2].move(sf::Vector2f(-10, 0));
				keyPressed = 1;
			}
		}
		else if (sf::Keyboard::isKeyPressed(sf::Keyboard::D))
		{
			if (!keyPressed)
			{
				sprite[2].move(sf::Vector2f(10, 0));
				keyPressed = 1;
			}
		}

		else if (sf::Keyboard::isKeyPressed(sf::Keyboard::Z))
		{
			if (!keyPressed)
			{
				sprite[2].move(sf::Vector2f(-100, 0));
				keyPressed = 1;
			}
		}
		else if (sf::Keyboard::isKeyPressed(sf::Keyboard::C))
		{
			if (!keyPressed)
			{
				sprite[2].move(sf::Vector2f(100, 0));
				keyPressed = 1;
			}
		}

		else if (sf::Keyboard::isKeyPressed(sf::Keyboard::Add))
		{
			if (!keyPressed)
			{
				view.zoom(0.9f);
				keyPressed = 1;

			}
		}
		else if (sf::Keyboard::isKeyPressed(sf::Keyboard::Subtract))
		{
			if (!keyPressed)
			{
				view.zoom(1.1f);
				keyPressed = 1;

			}
		}
		else if (sf::Keyboard::isKeyPressed(sf::Keyboard::Escape))
		{
			window.close();
		}
		else keyPressed = 0;



		sf::Event event;
		while (window.pollEvent(event))
		{
			if (event.type == sf::Event::Closed)
				window.close();
			else if (event.type == sf::Event::Resized)
			{
				view.setSize({
						static_cast<float>(event.size.width),
						static_cast<float>(event.size.height)
					});
				window.setView(view);
			}

		}


		text.setString(toString(sprite[2].getPosition().x) + "px to point 0");

		window.setView(view);
		window.clear();
		window.draw(tlo);
		window.draw(sprite[0]);
		window.draw(sprite[1]);
		window.draw(sprite[2]);
		window.draw(text);
		window.draw(text2);
		window.display();
	}

	return 0;
}
