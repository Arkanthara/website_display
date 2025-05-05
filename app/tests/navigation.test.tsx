import React from 'react';
import { describe, expect, test } from 'vitest';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router';
import Navigation from '../components/navigation';
import '@testing-library/jest-dom/vitest';

describe('<Navigation />', () => {
  test('renders navigation links correctly', () => {
   render(
     <MemoryRouter>
       <Navigation />
     </MemoryRouter>
   );
    expect(screen.getByText('Home')).toBeInTheDocument();
    expect(screen.getByText('Phony')).toBeInTheDocument();

  });
});
