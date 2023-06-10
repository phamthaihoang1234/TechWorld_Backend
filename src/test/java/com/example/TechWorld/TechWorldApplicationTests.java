package com.example.TechWorld;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TechWorldApplicationTests {

	@Test
	void contextLoads() {
	}

	class Animal {
		public void makeSound() {
			System.out.println("Animal is making a sound");
		}
	}

	class Cat extends Animal {
		@Override
		public void makeSound() {
			super.makeSound(); // Gọi phương thức makeSound() của lớp cha
			System.out.println("Cat is meowing");
		}
	}

	public void main(String[] args) {
		Cat c = new Cat();
		c.makeSound();

	}

}
