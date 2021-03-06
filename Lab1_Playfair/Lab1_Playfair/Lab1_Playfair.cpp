#include "pch.h"
#include <iostream>
#include <string>
#include <array>
#include <vector>

const std::vector<char> alfabet = { 'a','b','c','d','e','f','g','h','i','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };

bool czy_jest_litera(char litera, const std::string &klucz)
{
	for (int i = 0; i < klucz.size();)
	{
		if (litera == klucz[i])
		{
			return true;
		}
		else
		{
			i++;
		}
	}
	return false;
}
std::vector<char> tablica(const std::string &klucz)
{
	std::vector<char> tab;
	for (int i = 0; i < klucz.size(); i++)
	{
		tab.push_back(klucz[i]);
	}
	for (int i = 0; i < alfabet.size(); i++)
	{
		if (czy_jest_litera(alfabet[i], klucz) == false)
		{
			tab.push_back(alfabet[i]);
		}
	}
	return tab;
}
void wyswietl(std::vector<char> &tablica)
{
	for (int i = 0; i < tablica.size(); i++)
	{
		std::cout << "[" << tablica[i] << "] ";
		if (i % 5 == 4)
		{
			std::cout << std::endl;
		}
	}
}
std::string przygotowanie_slowa_do_szyfrowania(const std::string &klucz)
{
	std::string d = klucz;
	int ile_dodac = 1;
	for (int i = 0; i < klucz.size() - 1; i++)
	{
		if (klucz[i] == klucz[i + 1])
		{
			d.insert(d.begin() + i + ile_dodac, 'x');
			ile_dodac++;

		}
	}
	if (d.size() % 2 == 1)
	{
		d.push_back('x');
	}
	for (int i = 0; i < d.size(); i++)
	{
		if (d[i] == 'j') d[i] = 'i';
	}

	return d;
}
int numer_miejsca(char litera, const std::vector<char> & tablica_szyfrowana)
{
	int nm = 0;
	for (int i = 0; i < tablica_szyfrowana.size(); i++)
	{
		if (litera == tablica_szyfrowana[i])
		{
			nm = i;

		}
	}
	return nm;
}

std::string po_zaszyfrowaniu(const std::vector<char> & tablica_szyfrowana, const std::string & haslo)
{
	std::string p;
	int numer_kolumny1;
	int numer_kolumny2;
	int numer_wiersza1;
	int numer_wiersza2;
	for (int i = 0; i < haslo.size() - 1; i = i + 2)
	{
		numer_wiersza1 = numer_miejsca(haslo[i], tablica_szyfrowana) / 5;
		numer_wiersza2 = numer_miejsca(haslo[i + 1], tablica_szyfrowana) / 5;
		numer_kolumny1 = numer_miejsca(haslo[i], tablica_szyfrowana) % 5;
		numer_kolumny2 = numer_miejsca(haslo[i + 1], tablica_szyfrowana) % 5;
		auto it1 = std::find(tablica_szyfrowana.begin(), tablica_szyfrowana.end(), haslo[i]);
		auto it2 = std::find(tablica_szyfrowana.begin(), tablica_szyfrowana.end(), haslo[i + 1]);
		int poz1 = it1 - tablica_szyfrowana.begin();
		int poz2 = it2 - tablica_szyfrowana.begin();

		if (numer_wiersza1 == numer_wiersza2)
		{
			//if (numer_wiersza1 < 4)
			{
				if ((poz1 + 1) / 5 != numer_wiersza1)
				{
					poz1 = poz1 - 5;
				}
				if ((poz2 + 1) / 5 != numer_wiersza1)
				{
					poz2 = poz2 - 5;
				}
				p.push_back(tablica_szyfrowana[poz1 + 1]);
				p.push_back(tablica_szyfrowana[poz2 + 1]);
			}
		}
		else if (numer_kolumny1 == numer_kolumny2)
		{
			p.push_back(tablica_szyfrowana[(poz1 + 5) % 25]);
			p.push_back(tablica_szyfrowana[(poz2 + 5) % 25]);
		}
		else
		{
			p.push_back(tablica_szyfrowana[(numer_wiersza1 * 5) + numer_kolumny2]);
			p.push_back(tablica_szyfrowana[(numer_wiersza2 * 5) + numer_kolumny1]);
		}
	}
	return p;
}

std::string usuniecie_x(const std::string &haslo)
{
	std::string u;
	for (int i = 0; i < haslo.size(); i++)
	{
		if (haslo[i] != 'x')
		{
			u.push_back(haslo[i]);
		}
	}
	return u;
}

std::string po_deszyfrowaniu(const std::vector<char> & tablica_szyfrowana, const std::string & haslo)
{
	std::string p;
	int numer_kolumny1;
	int numer_kolumny2;
	int numer_wiersza1;
	int numer_wiersza2;
	for (int i = 0; i < haslo.size() - 1; i = i + 2)
	{
		numer_wiersza1 = numer_miejsca(haslo[i], tablica_szyfrowana) / 5;
		numer_wiersza2 = numer_miejsca(haslo[i + 1], tablica_szyfrowana) / 5;
		numer_kolumny1 = numer_miejsca(haslo[i], tablica_szyfrowana) % 5;
		numer_kolumny2 = numer_miejsca(haslo[i + 1], tablica_szyfrowana) % 5;
		auto it1 = std::find(tablica_szyfrowana.begin(), tablica_szyfrowana.end(), haslo[i]);
		auto it2 = std::find(tablica_szyfrowana.begin(), tablica_szyfrowana.end(), haslo[i + 1]);
		int poz1 = it1 - tablica_szyfrowana.begin();
		int poz2 = it2 - tablica_szyfrowana.begin();

		if (numer_wiersza1 == numer_wiersza2)
		{
			//if (numer_wiersza1 < 4)
			{
				if ((poz1 - 1) / 5 != numer_wiersza1)
				{
					poz1 = poz1 + 5;
				}
				if ((poz2 - 1) / 5 != numer_wiersza1)
				{
					poz2 = poz2 + 5;
				}
				p.push_back(tablica_szyfrowana[poz1 - 1]);
				p.push_back(tablica_szyfrowana[poz2 - 1]);
			}
		}
		else if (numer_kolumny1 == numer_kolumny2)
		{
			p.push_back(tablica_szyfrowana[(poz1 - 5) % 25]);
			p.push_back(tablica_szyfrowana[(poz2 - 5) % 25]);
		}
		else
		{
			p.push_back(tablica_szyfrowana[(numer_wiersza1 * 5) + numer_kolumny2]);
			p.push_back(tablica_szyfrowana[(numer_wiersza2 * 5) + numer_kolumny1]);
		}
	}
	return p;
}

int main()
{
	std::string klucz = "klucz";
	std::cout << "Klucz: " << klucz << std::endl;
	std::vector<char> tab = tablica(klucz);
	std::cout << "Tablica: " << std::endl;
	wyswietl(tab);
	std::string haslo = "madonna";
	haslo = przygotowanie_slowa_do_szyfrowania(haslo);
	std::cout << "Haslo do szyfrowania: " << haslo << std::endl;
	std::string haslo_po_szyfrowaniu = po_zaszyfrowaniu(tab, haslo);
	std::cout << "Haslo po rozszyfrowaniu: " << haslo_po_szyfrowaniu << std::endl;
	std::string haslo_po_deszyfrowaniu = po_deszyfrowaniu(tab, haslo_po_szyfrowaniu);
	std::string haslo_po_deszyfrowaniu_bez_x=usuniecie_x(haslo_po_deszyfrowaniu);
	std::cout << "Haslo po deszyfrowaniu: " << haslo_po_deszyfrowaniu_bez_x << std::endl;

}


