<?php

namespace Tests\Unit;

use Tests\TestCase;

class AuthenticationTest extends TestCase
{
  private $api_koperasi = 'https://koperasi.yacanet.com/api/v1';
  /**
   * test login berhasil
   */
  public function test_auth_get_token(): void
  {
    $response = $this->withHeaders([
      
    ])
    ->get($this->api_koperasi . '/auth/login');
    
    $response->assertStatus(200);
    
  }
}
